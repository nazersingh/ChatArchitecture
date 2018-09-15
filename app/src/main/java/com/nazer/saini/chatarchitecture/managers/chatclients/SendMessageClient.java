package com.nazer.saini.chatarchitecture.managers.chatclients;

import android.os.CountDownTimer;
import android.os.Handler;

import com.nazer.saini.chatarchitecture.managers.socket.SocketManager;
import com.nazer.saini.chatarchitecture.pojomodels.ChatMediaType;
import com.nazer.saini.chatarchitecture.pojomodels.basemodels.ChatMessage;
import com.nazer.saini.chatarchitecture.utils.Constants;

import java.util.HashMap;

import io.socket.client.Ack;


public class SendMessageClient {

    private SendMessageCallback sendMessageCallback;

    /**
     * it's for demo purpose
     */
    private int uid = 0;

    /**
     * Initialize send send message client
     */
    public SendMessageClient(SendMessageCallback sendMessageCallback) {
        this.sendMessageCallback = sendMessageCallback;
    }

    /**
     * Save message in local table before sending it to server
     *
     * @param msgBody
     */
    public void sendMessageToLocal(ChatMessage msgBody) {

//        sendMessageCallback.onLocalMessageSend(msgBody);

        callDemoChat(msgBody);
    }

    /**
     * Fetch message from local table to send on server
     * If there is no message then send callback that all messages are sent
     */
    public void fetchPendingMessagesToSendServer() {

    }

    /**
     * Once the message is fetched from local table, send it to server
     *
     * @param message
     */
    private void sendMessageToServer(ChatMessage message) {

        HashMap<String, String> sendMessagePayload = new HashMap<>();
        sendMessagePayload.put("message", message.getMessageBody());
        sendMessagePayload.put("chatId", message.getMessageBody());

        SocketManager.getInstance().getSocketInstance().emit(Constants.SEND_MESSAGE, sendMessagePayload, new Ack() {
            @Override
            public void call(Object... args) {

//                sendMessageCallback.onServerMessageSend(message);
            }
        });

        sendMessageCallback.onServerMessageSend(message);

    }


    interface SendMessageCallback {
        void onLocalMessageSend(ChatMessage chatMessage);

        void onServerMessageSend(ChatMessage chatMessage);

        void onMediaMessageUploadProgress();
    }


    /**
     * ========================================    Delete it after demo
     */
    public void startDemoChating() {


        ChatMessage chatMessage = new ChatMessage();

        chatMessage.setMessageBody("Test Message to send");

        chatMessage.setMessageType(ChatMediaType.TEXT);
        if (uid % 4 == 0)
            chatMessage.setTempIsMessageTypeSend(true);
        else
            chatMessage.setTempIsMessageTypeSend(false);
        callDemoChat(chatMessage);


    }

    /**
     * 4 seconds interval
     */
    public void callDemoChat(final ChatMessage chatMessage) {

        chatMessage.setUid(uid);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                chatMessage.setLocalMessage(true);
                sendMessageCallback.onLocalMessageSend(chatMessage);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        chatMessage.setLocalMessage(false);
                        if(chatMessage.getMessageType().equals(ChatMediaType.IMAGE_TEXT))
                            chatMessage.setRemoteUrl("https://i2.wp.com/beebom.com/wp-content/uploads/2016/01/Reverse-Image-Search-Engines-Apps-And-Its-Uses-2016.jpg?resize=640%2C426");

                        sendMessageCallback.onServerMessageSend(chatMessage);
                        uid = uid + 1;
                        startDemoChating();
                    }
                }, 1000);

            }
        }, 2000);

    }
}
