package com.nazer.saini.chatarchitecture.managers.chatclients;

import android.os.SystemClock;

import com.nazer.saini.chatarchitecture.managers.socket.SocketManager;
import com.nazer.saini.chatarchitecture.pojomodels.basemodels.ChatMessage;

import java.util.ArrayList;


public class ChatManager implements SendMessageClient.SendMessageCallback,
        ReceiveMessageClient.ReceiveMessageClientCallback, SocketManager.SocketManagerCallBack {


    private static ChatManager chatManager;
    private ChatManagerCallback chatManagerCallback;
    private SendMessageClient sendMessageClient = new SendMessageClient(this);
    private ReceiveMessageClient receiveMessageClient = new ReceiveMessageClient(this);

    private long mLastTime = 0;


    public ChatManager(ChatManagerCallback chatManagerCallback) {
        this.chatManagerCallback = chatManagerCallback;
    }


    /**
     * Called when socket get connected
     */
    @Override
    public void onConnected() {
//        sendMessageClient.fetchPendingMessagesToSendServer();
        receiveMessageClient.registerReceiver();
    }


    /**
     * Send message to SendMessageClient, for sending it to server and saving in realm table
     */
    public void sendMessage(ChatMessage msgBody) {
        sendMessageClient.sendMessageToLocal(msgBody);
    }

    /**
     * Send Input to upload Media CLient
     *
     * @param mediaList
     */
    public void sendMediaFile(ArrayList<ChatMessage> mediaList) {
//        uploadingMediaClient.sendMediaToLocal(mediaList);
    }


    /**
     * Whether uploading or downloading, update progress for chat media message
     *
     * @param percentage
     * @param chatMessage
     */
    private void updateMediaProgress(float percentage, ChatMessage chatMessage) {
        // Send progress only after 2 seconds
        if (SystemClock.elapsedRealtime() - mLastTime < 2000) {
            return;
        }
        mLastTime = SystemClock.elapsedRealtime();
//        chatManagerCallback.onMediaProgressReceived(percentage, chatMessage);
    }

    @Override
    public void onLocalMessageSend(ChatMessage chatMessage) {
        chatManagerCallback.onMessageSendLocal(chatMessage);
    }

    @Override
    public void onServerMessageSend(ChatMessage chatMessage) {
        chatManagerCallback.onMessageSendServer(chatMessage);
    }

    @Override
    public void onMediaMessageUploadProgress() {

    }

    @Override
    public void onMessageReceived(ChatMessage chatMessage) {
        chatManagerCallback.onMessageReceived(chatMessage);
    }


    public interface ChatManagerCallback {
        void onMessageReceived(ChatMessage chatMessage);

        void onMessageSendLocal(ChatMessage chatMessage);

        void onMessageSendServer(ChatMessage chatMessage);

        void onMediaProgressReceived(float percentage, ChatMessage chatMessage);
    }


}
