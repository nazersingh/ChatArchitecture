package com.nazer.saini.chatarchitecture.ui.fragments.chat;


import com.nazer.saini.chatarchitecture.pojomodels.basemodels.ChatMessage;

import java.util.ArrayList;


public class ChatPresenter implements ChatModel.ChatModelCallback {

    ChatModel chatModel;
    ChatPresenterCallback chatPresenterCallback;

    public ChatPresenter(ChatPresenterCallback chatPresenterCallback) {
        chatModel = new ChatModel(this);
        this.chatPresenterCallback = chatPresenterCallback;
    }

    public void sendMessage(ChatMessage msgBody) {
        chatModel.sendMessage(msgBody);
    }

    public void sendMultipleMediaMessage(ArrayList<ChatMessage> mediaList) {
        chatModel.sendMultyMediaMessage(mediaList);
    }

    @Override
    public void getAllMessagesList(ArrayList<ChatMessage> chatMessages) {
        chatPresenterCallback.getAllMessagesList(chatMessages);
    }

    /**
     * Get all remote and local messages
     */
    public void fetchChatRoomMessages() {
        chatModel.fetchChatRoomMessages();

    }

    public interface ChatPresenterCallback {
        void getAllMessagesList(ArrayList<ChatMessage> chatMessages);
    }

}
