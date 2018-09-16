package com.nazer.saini.chatarchitecture.ui.fragments.chat;


import com.nazer.saini.chatarchitecture.application.MyApplication;
import com.nazer.saini.chatarchitecture.managers.chatclients.AppRoomDatabase;
import com.nazer.saini.chatarchitecture.pojomodels.basemodels.ChatMessage;

import java.util.ArrayList;


public class ChatModel {

//    private ChatRealmClient chatRealmClient = new ChatRealmClient();
    private ChatModelCallback chatModelCallback;

    public ChatModel(ChatModelCallback chatModelCallback) {
        this.chatModelCallback = chatModelCallback;
    }

    public void sendMessage(ChatMessage msgBody) {
        MyApplication.getInstance().chatManager.sendMessage(msgBody);
    }

    /**
     * send this media message to chat manager
     */
    public void sendMultyMediaMessage(ArrayList<ChatMessage> mediaList) {
        MyApplication.getInstance().chatManager.sendMediaFile(mediaList);
    }

    /**
     * fetch all messages from  local plus remote table
     * sort messages according to localId in ascending order
     */
    public void fetchChatRoomMessages() {
        ArrayList<ChatMessage> chatMessages = (ArrayList<ChatMessage>) AppRoomDatabase.getAppDatabase(MyApplication.getInstance()).chatDatabaseDao().getAll();
        chatModelCallback.getAllMessagesList(chatMessages);
    }

    public interface ChatModelCallback {
        void getAllMessagesList(ArrayList<ChatMessage> chatMessages);
    }
}

