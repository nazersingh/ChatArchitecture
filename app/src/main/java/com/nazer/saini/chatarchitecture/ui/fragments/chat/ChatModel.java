package com.nazer.saini.chatarchitecture.ui.fragments.chat;


import com.nazer.saini.chatarchitecture.application.MyApplication;
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
     * fetch all messages from realm local plus remote table
     * sort messages according to localId in ascending order
     */
    public void fetchChatRoomMessages() {
//        ArrayList<ChatMessage> chatMessages = chatRealmClient.fetchAllRoomMessages();

//        Map<Long, ChatMessage> myTreeMap = new TreeMap<Long, ChatMessage>();
//        for (int i = 0; i < chatMessages.size(); i++) {
//            myTreeMap.put(chatMessages.get(i).getUid(), chatMessages.get(i));
//        }

//        ArrayList<ChatMessage> sortedChatMessages = new ArrayList<>(myTreeMap.values());
//        chatModelCallback.getAllMessagesList(sortedChatMessages);
    }

    public interface ChatModelCallback {
        void getAllMessagesList(ArrayList<ChatMessage> chatMessages);
    }
}

