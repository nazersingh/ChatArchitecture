package com.nazer.saini.chatarchitecture.ui.fragments.chat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.nazer.saini.chatarchitecture.R;
import com.nazer.saini.chatarchitecture.application.MyApplication;
import com.nazer.saini.chatarchitecture.managers.chatclients.ChatManager;
import com.nazer.saini.chatarchitecture.pojomodels.ChatMediaType;
import com.nazer.saini.chatarchitecture.pojomodels.basemodels.ChatMessage;
import com.nazer.saini.chatarchitecture.ui.activity.MainActivity;
import com.nazer.saini.chatarchitecture.ui.adapters.ChatAdapter;
import com.nazer.saini.chatarchitecture.utils.ImageVideoAudioPicker;
import com.nazer.saini.chatarchitecture.utils.PrintLog;

import java.io.IOException;
import java.security.AccessController;
import java.util.ArrayList;


public class ChatFragment extends Fragment implements View.OnClickListener{

    private String TAG = "ChatFragment";

    private static final int PICK_IMAGE = 101;
    private static final int REQUEST_STORAGE_PERMISSION = 102;

    private RecyclerView mRvRecyclerView;
    private EditText mEtMessage;
    private ImageView mIvUploadImage;
    private Button mBtSend;
    private View mLayoutBubble;
    public TextView mTvTimeBubble;

    private ArrayList<ChatMessage> mMessageList = new ArrayList<>();
    private ArrayList<Long> localIdsList = new ArrayList<>();

    private ChatAdapter mAdapter;

    private ChatPresenter chatPresenter;
    private View view;
    private LinearLayoutManager linearLayoutManager;
    private String roomId = "1";


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //init Presenter
        chatPresenter = new ChatPresenter(chatPresenterCallback);
        //init Presenter
    }

    ChatPresenter.ChatPresenterCallback  chatPresenterCallback=new ChatPresenter.ChatPresenterCallback() {
        @Override
        public void getAllMessagesList(ArrayList<ChatMessage> chatMessages) {

        }
    };


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (view == null) {
            view = inflater.inflate(R.layout.fragment_chat, container, false);

            init();
            initAdapter();
        }
        return view;


    }

    @Override
    public void onResume() {
        super.onResume();
//        chatPresenter.fetchChatRoomMessages();
        MyApplication.getInstance().setChatScreenVisible(true);
        MyApplication.getInstance().setChatScreenVisibleCallBack(chatManagerCallback);
    }

    private void init() {
        mRvRecyclerView = view.findViewById(R.id.mRvRecyclerView);
        mEtMessage = view.findViewById(R.id.mEtMessage);
        mIvUploadImage = view.findViewById(R.id.mIvUploadImage);
        mBtSend = view.findViewById(R.id.mBtSend);
        mLayoutBubble = view.findViewById(R.id.bubble_view);
        mTvTimeBubble = view.findViewById(R.id.time_bubble);

        mBtSend.setOnClickListener(this);
        mIvUploadImage.setOnClickListener(this);
    }

    private void initAdapter() {

        mAdapter = new ChatAdapter(getActivity(), mMessageList);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        mRvRecyclerView.setLayoutManager(linearLayoutManager);
        mAdapter.hasStableIds();
        mRvRecyclerView.setAdapter(mAdapter);
        mRvRecyclerView.setItemAnimator(null);
        mRvRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);


            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mBtSend:
                if (!TextUtils.isEmpty(mEtMessage.getText().toString().trim())) {
                    String newMessage = mEtMessage.getText().toString();
                    ChatMessage chatMessage=new ChatMessage();
                    chatMessage.setMessageBody(newMessage);
                    chatMessage.setMessageType(ChatMediaType.TEXT);
                    chatMessage.setTempIsMessageTypeSend(true);
                    chatPresenter.sendMessage(chatMessage);
                    mRvRecyclerView.scrollToPosition(mMessageList.size() - 1);
                    mEtMessage.setText("");
                }
                break;
            case R.id.mIvUploadImage:
                if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            REQUEST_STORAGE_PERMISSION);
                } else {
                    ImageVideoAudioPicker.getInstance().showImagePickerDialogue((getActivity()));
                }
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_CANCELED) {
            return;
        }
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == ImageVideoAudioPicker.GALLERY_IMAGE_REQUEST || requestCode == ImageVideoAudioPicker.CAMERA_IMAGE_REQUEST) {
                if (requestCode == ImageVideoAudioPicker.CAMERA_IMAGE_REQUEST) {
                    try {
                        Bitmap bitmap= ImageVideoAudioPicker.getInstance().getBitmapFromResult(getActivity(), requestCode, resultCode, data);
                        PrintLog.e(TAG, " save camera image file " + ImageVideoAudioPicker.getInstance().saveImageAndGetPath(getActivity(), bitmap));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    PrintLog.e(TAG, " gallery image file " + ImageVideoAudioPicker.getInstance().getRealPathFromUri(getActivity(), data.getData()));
                    ChatMessage chatMessage=new ChatMessage();
                    chatMessage.setMessageType(ChatMediaType.IMAGE_TEXT);
                    chatMessage.setTempIsMessageTypeSend(true);
                    chatMessage.setLocalUrl(ImageVideoAudioPicker.getInstance().getRealPathFromUri(getActivity(), data.getData()));
                    chatPresenter.sendMessage(chatMessage);
                }
            }
            if (requestCode == ImageVideoAudioPicker.GALLERY_VIDEO_REQUEST || requestCode == ImageVideoAudioPicker.CAMERA_VIDEO_REQUEST) {
                if (requestCode == ImageVideoAudioPicker.CAMERA_VIDEO_REQUEST) {
                    PrintLog.e(TAG, "Camera Video File " + ImageVideoAudioPicker.getInstance().getRealPathFromUri(getActivity(),data.getData()));
                } else {
                    PrintLog.e(TAG, " gallery Video file " + ImageVideoAudioPicker.getInstance().getRealPathFromUri(getActivity(), data.getData()));
                }
            }
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    ChatManager.ChatManagerCallback chatManagerCallback=new ChatManager.ChatManagerCallback() {
        @Override
        public void onMessageReceived(ChatMessage chatMessage) {



        }

        @Override
        public void onMessageSendLocal(ChatMessage chatMessage) {
            mMessageList.add(chatMessage);
            mAdapter.notifyDataSetChanged();
            mRvRecyclerView.smoothScrollToPosition(mMessageList.size());
        }

        @Override
        public void onMessageSendServer(ChatMessage chatMessage) {

            mMessageList.set((int) chatMessage.getUid(),chatMessage);
            mAdapter.notifyItemChanged((int) chatMessage.getUid());
            mRvRecyclerView.smoothScrollToPosition(mMessageList.size());
        }

        @Override
        public void onMediaProgressReceived(float percentage, ChatMessage chatMessage) {

        }
    };
}
