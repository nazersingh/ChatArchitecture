package com.nazer.saini.chatarchitecture.application;

import android.app.Application;

import com.nazer.saini.chatarchitecture.managers.chatclients.ChatManager;
import com.nazer.saini.chatarchitecture.managers.chatclients.ChatManager.ChatManagerCallback;
import com.nazer.saini.chatarchitecture.managers.fcm.NotificationUtility;
import com.nazer.saini.chatarchitecture.managers.socket.SocketManager;
import com.nazer.saini.chatarchitecture.pojomodels.basemodels.ChatMessage;
import com.nazer.saini.chatarchitecture.ui.activity.MainActivity;

import java.util.HashMap;


public class MyApplication extends Application implements ChatManagerCallback {

    public ChatManager chatManager;
    private static MyApplication instance;
    private MainActivity mainActivity;
    private boolean isChatScreenVisible=true;
    private ChatManagerCallback chatScreenChatManagerCallback;

    @Override
    public void onCreate() {
        super.onCreate();

        // Initialize Realm (just once per application)
        instance = this;
        chatManager = new ChatManager(this);

        initializeSocket();
    }

    public static MyApplication getInstance() {
        return instance;
    }

    public void initializeSocket() {
        SocketManager.getInstance().initialiseSocket();
    }


    @Override
    public void onMessageReceived(ChatMessage chatMessage) {
        if (mainActivity != null) {
            if (isChatScreenVisible)
                chatScreenChatManagerCallback.onMessageReceived(chatMessage);
        } else {

            HashMap<String, String> stringStringMap = new HashMap<>();
            stringStringMap.put("body", "First Notification");
            stringStringMap.put("image", "https://i2.wp.com/beebom.com/wp-content/uploads/2016/01/Reverse-Image-Search-Engines-Apps-And-Its-Uses-2016.jpg?resize=640%2C426");
            stringStringMap.put("title", "Notification Title");
            stringStringMap.put("expandContent", "Expand Content is here");
            stringStringMap.put("notificationType", "2");
            NotificationUtility.getInstance().sendNotificationOnlyText(getApplicationContext(), stringStringMap);
        }
    }

    @Override
    public void onMessageSendLocal(ChatMessage chatMessage) {
        if (mainActivity != null) {
            if (isChatScreenVisible)
                chatScreenChatManagerCallback.onMessageSendLocal(chatMessage);
        }
    }

    @Override
    public void onMessageSendServer(ChatMessage chatMessage) {
        if (mainActivity != null) {
            if (isChatScreenVisible)
                chatScreenChatManagerCallback.onMessageSendServer(chatMessage);
        }
    }


    @Override
    public void onMediaProgressReceived(float percentage, ChatMessage chatMessage) {

    }

    public void setChatScreenVisible(boolean b) {
        isChatScreenVisible = b;
    }

    public void setChatScreenVisibleCallBack(ChatManagerCallback chatManagerCallback) {
        this.chatScreenChatManagerCallback = chatManagerCallback;
    }
    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }
}
