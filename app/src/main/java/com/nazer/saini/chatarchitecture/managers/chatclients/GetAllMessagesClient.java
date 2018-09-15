package com.nazer.saini.chatarchitecture.managers.chatclients;

import android.os.AsyncTask;
import android.os.Handler;

import com.nazer.saini.chatarchitecture.pojomodels.basemodels.ChatMessage;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class GetAllMessagesClient {

    private ArrayList<ChatMessage> chatMessages = new ArrayList<>();

    private GetAllMessagesClientCallback getAllMessagesClientCallback;

    /**
     * Initialize Get all messages client for receiving messages
     */
    public GetAllMessagesClient(GetAllMessagesClientCallback getAllMessagesClientCallback) {
        this.getAllMessagesClientCallback = getAllMessagesClientCallback;
    }


    /**
     * Method for getting all messages
     * We will query realm db for getting maximum time period message, currently we have in or db.
     * Then we will query socket for getting all messages after that time period
     */
    public void getAllMessages() {

//        isGetAllExecuting = true;
//        startRunnable();
//
//        //Fetch max time in local and remote table
//        long maxTimeInDB = chatRealmClient.fetchMaxTimeRemoteTable();
//
//        JSONObject jsonObject = new JSONObject();
//        try {
//            jsonObject.put("time", maxTimeInDB);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        socketManager.emitData(Constants.GET_ALL_MESSAGES, jsonObject);


        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                getAllMessagesClientCallback.onAllMessageFetchedFromServer();
            }
        });

    }

    public interface GetAllMessagesClientCallback {
        void onAllMessageFetchedFromServer();
    }
}
