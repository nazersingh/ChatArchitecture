package com.nazer.saini.chatarchitecture.managers.chatclients;

import android.os.AsyncTask;


import com.nazer.saini.chatarchitecture.pojomodels.basemodels.ChatMessage;

import java.util.ArrayList;



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
