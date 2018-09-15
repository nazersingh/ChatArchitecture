package com.nazer.saini.chatarchitecture.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nazer.saini.chatarchitecture.R;
import com.nazer.saini.chatarchitecture.pojomodels.basemodels.ChatMessage;
import com.nazer.saini.chatarchitecture.utils.Constants;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class ChatAdapter extends RecyclerView.Adapter {

    private static final int MESSAGE_TYPE_SEND_TEXT = 1;
    private static final int MESSAGE_TYPE_SEND_IMAGE = 2;
    private static final int MESSAGE_TYPE_SEND_VIDEO = 3;
    private static final int MESSAGE_TYPE_RECEIVE_TEXT = 4;
    private static final int MESSAGE_TYPE_RECEIVE_IMAGE = 5;
    private static final int MESSAGE_TYPE_RECEIVE_VIDEO = 6;

    private Context mContext;
    private ArrayList<ChatMessage> mMessageList;

    public ChatAdapter(Context context, ArrayList<ChatMessage> chatMessages) {
        this.mContext = context;
        this.mMessageList = chatMessages;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;

        if (viewType == MESSAGE_TYPE_SEND_TEXT) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_message_sent_text, parent, false);
            return new SentTextMessageHolder(view);
        } else if (viewType == MESSAGE_TYPE_SEND_IMAGE) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_message_sent_image, parent, false);
            return new SentImageMessageHolder(view);
        } else if (viewType == MESSAGE_TYPE_RECEIVE_TEXT) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_message_received, parent, false);
            return new ReceivedMessageHolder(view);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        ChatMessage chatMessage = mMessageList.get(position);

        boolean shouldTimeBubbleDisplay = false;
        if (position != 0) {
            long previousMessageTime = mMessageList.get(position - 1).getTime();
            long currentMessageTime = mMessageList.get(position).getTime();

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd");

            Date previousMessageDate = new Date(previousMessageTime);
            Date currentMessageDate = new Date(currentMessageTime);

            if (!dateFormat.format(previousMessageDate).equals(dateFormat.format(currentMessageDate))) {
                shouldTimeBubbleDisplay = true;
            }
        }

        if (chatMessage.getTempIsMessageTypeSend()) {
            switch (chatMessage.getMessageType()) {
                case TEXT:
                    SentTextMessageHolder sentMessageHolder = (SentTextMessageHolder) holder;
                    sentMessageHolder.messageText.setText(chatMessage.getMessageBody());
                    if (!chatMessage.isLocalMessage()) {
                        sentMessageHolder.mIvStatus.setImageResource(R.drawable.ic_check_black_24dp);
                    } else {
                        sentMessageHolder.mIvStatus.setImageResource(R.drawable.ic_watch_later_black_24dp);
                    }

                    if (chatMessage.getTime() != 0) {
                        sentMessageHolder.mTvTime.setText(getformatDisplayTimeInString(chatMessage.getTime()));
                    }
                    if (shouldTimeBubbleDisplay) {
                        sentMessageHolder.mLayoutBubble.setVisibility(View.VISIBLE);
                        sentMessageHolder.mTvTimeBubble.setText(getFormatDisplayDateBubbleInString(chatMessage.getTime()));
                    } else {
                        sentMessageHolder.mLayoutBubble.setVisibility(View.GONE);
                    }
                    break;
                case IMAGE_TEXT:
                    SentImageMessageHolder sentImageMessageHolder = (SentImageMessageHolder) holder;
                    sentImageMessageHolder.captionText.setText(chatMessage.getMessageCaption());
                    if (!chatMessage.isLocalMessage()) {
                        if (!TextUtils.isEmpty(chatMessage.getRemoteUrl())) {
                            Glide.with(mContext).load(chatMessage.getRemoteUrl()).into(sentImageMessageHolder.mIvContent);
                        }
                        sentImageMessageHolder.mIvStatus.setImageResource(R.drawable.ic_check_black_24dp);
                    } else {
                        if (!TextUtils.isEmpty(chatMessage.getLocalUrl())) {
                            Glide.with(mContext).load(chatMessage.getLocalUrl()).into(sentImageMessageHolder.mIvContent);
                        }
                        sentImageMessageHolder.mIvStatus.setImageResource(R.drawable.ic_watch_later_black_24dp);
                    }



                    if (chatMessage.getTime() != 0) {
                        sentImageMessageHolder.mTvTime.setText(getformatDisplayTimeInString(chatMessage.getTime()));
                    }
                    if (shouldTimeBubbleDisplay) {
                        sentImageMessageHolder.mLayoutBubble.setVisibility(View.VISIBLE);
                        sentImageMessageHolder.mTvTimeBubble.setText(getFormatDisplayDateBubbleInString(chatMessage.getTime()));
                    } else {
                        sentImageMessageHolder.mLayoutBubble.setVisibility(View.GONE);
                    }
                    break;
                case VIDEO_TEXT:

                    break;
            }

        } else {
            ReceivedMessageHolder receivedMessageHolder = (ReceivedMessageHolder) holder;
            receivedMessageHolder.messageText.setText(chatMessage.getMessageBody());

            if (chatMessage.getTime() != 0) {
                receivedMessageHolder.mTvTime.setText(getformatDisplayTimeInString(chatMessage.getTime()));
            }
            if (shouldTimeBubbleDisplay) {
                receivedMessageHolder.mLayoutBubble.setVisibility(View.VISIBLE);
                receivedMessageHolder.mTvTimeBubble.setText(getFormatDisplayDateBubbleInString(chatMessage.getTime()));
            } else {
                receivedMessageHolder.mLayoutBubble.setVisibility(View.GONE);
            }
        }
    }

    private String getformatDisplayTimeInString(long time) {
        Date date = new Date(time);
        SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm:ss a");
        String dateString = dateFormat.format(date);
        return dateString;
    }

    private String getFormatDisplayDateBubbleInString(long time) {
        Date date = new Date(time);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy MMMM, dd");
        String dateString = dateFormat.format(date);
        return dateString;
    }

    @Override
    public int getItemCount() {
        return mMessageList.size();
    }

    @Override
    public int getItemViewType(int position) {
        ChatMessage message = mMessageList.get(position);
        if (message.getTempIsMessageTypeSend()) {
            switch (message.getMessageType()) {
                case TEXT:
                    return MESSAGE_TYPE_SEND_TEXT;
                case IMAGE_TEXT:
                    return MESSAGE_TYPE_SEND_IMAGE;
                case VIDEO_TEXT:
                    return MESSAGE_TYPE_SEND_VIDEO;
            }
        } else if (!message.getTempIsMessageTypeSend()) {
            switch (message.getMessageType()) {
                case TEXT:
                    return MESSAGE_TYPE_RECEIVE_TEXT;
                case IMAGE_TEXT:
                    return MESSAGE_TYPE_RECEIVE_IMAGE;
                case VIDEO_TEXT:
                    return MESSAGE_TYPE_RECEIVE_VIDEO;
            }
        }
        return 0;
    }

    public class SentTextMessageHolder extends RecyclerView.ViewHolder {

        public TextView messageText;
        public TextView mTvTime;
        public ImageView mIvStatus;
        private View mLayoutBubble;
        public TextView mTvTimeBubble;

        public SentTextMessageHolder(View itemView) {
            super(itemView);
            messageText = itemView.findViewById(R.id.text_message_body);
            mTvTime = itemView.findViewById(R.id.text_message_time);
            mIvStatus = itemView.findViewById(R.id.mIvStatus);
            mLayoutBubble = itemView.findViewById(R.id.bubble_view);
            mTvTimeBubble = itemView.findViewById(R.id.time_bubble);
        }
    }

    public class SentImageMessageHolder extends RecyclerView.ViewHolder {

        public TextView captionText;
        public TextView mTvTime;
        public ImageView mIvStatus;
        public ImageView mIvContent;

        private View mLayoutBubble;
        public TextView mTvTimeBubble;

        public SentImageMessageHolder(View itemView) {
            super(itemView);
            captionText = itemView.findViewById(R.id.text_caption_body);
            mTvTime = itemView.findViewById(R.id.text_message_time);
            mIvStatus = itemView.findViewById(R.id.mIvStatus);
            mIvContent = itemView.findViewById(R.id.mIvContent);

            mLayoutBubble = itemView.findViewById(R.id.bubble_view);
            mTvTimeBubble = itemView.findViewById(R.id.time_bubble);
        }
    }

    public class ReceivedMessageHolder extends RecyclerView.ViewHolder {

        public TextView messageText;
        public TextView mTvTime;
        private View mLayoutBubble;
        public TextView mTvTimeBubble;

        public ReceivedMessageHolder(View itemView) {
            super(itemView);
            messageText = itemView.findViewById(R.id.text_message_body);
            mTvTime = itemView.findViewById(R.id.text_message_time);
            mLayoutBubble = itemView.findViewById(R.id.bubble_view);
            mTvTimeBubble = itemView.findViewById(R.id.time_bubble);
        }
    }

}
