package com.nazer.saini.chatarchitecture.managers.chatclients;

import android.os.CountDownTimer;
import android.os.Handler;

import com.nazer.saini.chatarchitecture.application.MyApplication;
import com.nazer.saini.chatarchitecture.managers.socket.SocketManager;
import com.nazer.saini.chatarchitecture.pojomodels.ChatMediaType;
import com.nazer.saini.chatarchitecture.pojomodels.basemodels.ChatMessage;
import com.nazer.saini.chatarchitecture.utils.Constants;
import com.nazer.saini.chatarchitecture.utils.PrintLog;

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
     * @param chatMessage
     */
    public void sendMessageToLocal(final ChatMessage chatMessage) {

        chatMessage.setIsLocalMessage(1);
        final long id=AppRoomDatabase.getAppDatabase(MyApplication.getInstance()).chatDatabaseDao().insert(chatMessage);
        PrintLog.e("Send Message "," Insert id "+id);
        sendMessageCallback.onLocalMessageSend(chatMessage);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                chatMessage.setIsLocalMessage(0);
                if (chatMessage.getMessageType().equals(ChatMediaType.IMAGE_TEXT))
                    chatMessage.setRemoteUrl("https://i2.wp.com/beebom.com/wp-content/uploads/2016/01/Reverse-Image-Search-Engines-Apps-And-Its-Uses-2016.jpg?resize=640%2C426");

                AppRoomDatabase.getAppDatabase(MyApplication.getInstance()).chatDatabaseDao().update(0,id);

                sendMessageCallback.onServerMessageSend(chatMessage);
            }
        }, 1000);
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


}
