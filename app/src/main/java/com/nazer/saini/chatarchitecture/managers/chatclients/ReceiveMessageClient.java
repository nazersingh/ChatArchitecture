package com.nazer.saini.chatarchitecture.managers.chatclients;

import android.os.AsyncTask;
import android.os.Handler;


import com.nazer.saini.chatarchitecture.application.MyApplication;
import com.nazer.saini.chatarchitecture.managers.socket.SocketManager;
import com.nazer.saini.chatarchitecture.pojomodels.ChatMediaType;
import com.nazer.saini.chatarchitecture.pojomodels.basemodels.ChatMessage;
import com.nazer.saini.chatarchitecture.utils.Constants;
import com.nazer.saini.chatarchitecture.utils.PrintLog;

import java.util.ArrayList;

import io.socket.emitter.Emitter;


public class ReceiveMessageClient {


    private ReceiveMessageClientCallback receiveMessageClientCallback;
    private ArrayList<ChatMessage> chatMessages = new ArrayList<>();
    private SaveMessagesTask saveMessagesTask = null;

    ChatMessage chatMessage=new ChatMessage();

    /**
     * Initialize Receive Message Client and initialize callback for sending results
     *
     * @param receiveMessageClientCallback
     */
    public ReceiveMessageClient(ReceiveMessageClientCallback receiveMessageClientCallback) {
        this.receiveMessageClientCallback = receiveMessageClientCallback;

    }
public void registerReceiver()
{
//    SocketManager.getInstance().getSocketInstance().on(Constants.RECEIVE_MESSAGE, new Emitter.Listener() {
//        @Override
//        public void call(Object... args) {
//            receiveMessageClientCallback.onMessageReceived();
//        }
//    });

    new Handler().postDelayed(new Runnable() {
        @Override
        public void run() {
            ChatMessage chatMessage=new ChatMessage();
            chatMessage.setIsLocalMessage(Constants.LOCAL_MSG_FALSE);
            chatMessage.setMessageBody("it's Message Received");
            chatMessage.setTempIsMessageTypeSend(0);
            chatMessage.setTime(System.currentTimeMillis());
            chatMessage.setMessageType(String.valueOf(ChatMediaType.TEXT));

            final long id=AppRoomDatabase.getAppDatabase(MyApplication.getInstance()).chatDatabaseDao().insert(chatMessage);
            PrintLog.e("Receive Message "," Insert id "+id);

            receiveMessageClientCallback.onMessageReceived(chatMessage);
        }
    },5000);

}


    /**
     * Convert json message into Chat message object
     * Add new message in buffer list
     *
     * @param object
     */
    private void saveMessageInBufferList(Object object) {
//        JSONObject jsonObject = (JSONObject) object;
//
//        //Convert Json to Local Message
//        ChatMessage chatMessage = ChatUtility.getChatMessageFromJson(jsonObject);
//        chatMessages.add(chatMessage);
//        processIncomingMessage();
    }

    /**
     * Call save messages AsyncTask in recursion
     * for saving incoming messages one by one in the remote realm table.
     * If async task is already running then return
     */
    private void processIncomingMessage() {
        if (!chatMessages.isEmpty()) {
            if (saveMessagesTask != null && saveMessagesTask.getStatus() == AsyncTask.Status.RUNNING)
                return;
            else if (saveMessagesTask == null) {
                saveMessagesTask = new SaveMessagesTask();
                saveMessagesTask.execute();
            }
        }
    }

    /**
     * AsyncTask for saving new chat messages one by one in the remote realm table
     */
    private class SaveMessagesTask extends AsyncTask<Void, Void, Void> {


        @Override
        protected Void doInBackground(Void... param) {

            chatMessage = chatMessages.get(0);
//            long maxlocatId = chatRealmClient.getNewLocalMessageId();
//            chatMessage.setUid(maxlocatId);
//            chatMessage.setMessageBody("Recieved msg " + maxlocatId);

            //Save Message To Remote Table
//            chatRealmClient.saveMessageToRemoteDB(chatMessage);

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            //Send CallBack with Chat Message
            receiveMessageClientCallback.onMessageReceived(chatMessage);

            chatMessages.remove(0);
            saveMessagesTask = null;
            processIncomingMessage();
        }
    }

    public interface ReceiveMessageClientCallback {
        void onMessageReceived(ChatMessage chatMessage);
    }
}
