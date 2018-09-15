package com.nazer.saini.chatarchitecture.managers.socket;


import android.util.Log;

import com.google.gson.JsonObject;
import com.nazer.saini.chatarchitecture.application.MyApplication;
import com.nazer.saini.chatarchitecture.utils.Constants;

import org.json.JSONObject;

import java.net.URISyntaxException;
import java.util.HashMap;

import io.socket.client.Ack;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;


public class SocketManager {

    private static SocketManager socketManager;
    //    private static SocketManager socketManager;
    private static Socket mSocket;

    private final String TAG = this.getClass().getSimpleName();


    /**
     * Get singleton instance of socket manager
     *
     * @return
     */
    public static SocketManager getInstance() {
        if (socketManager == null) {
            socketManager = new SocketManager();
        }
        return socketManager;
    }

    public Socket getSocketInstance()
    {
        return mSocket;
    }


    /**
     * Initialize socket
     * Socket authentication
     * setup socket listeners
     */
    public void initialiseSocket() {
        try {
            if (mSocket == null) {
                mSocket = IO.socket(Constants.SOCKET_URL+Constants.PORT);
                setupSocketListener();
            } else if (!mSocket.connected()) {
                mSocket.connect();
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }


    /**
     * Listen to socket events and retreive data
     */
    private void setupSocketListener() {

//        mSocket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
//
//            @Override
//            public void call(Object... args) {
//
//                Log.e(TAG, "Socket.EVENT_CONNECT ");
//
//                HashMap<String,String> jsonObject=new HashMap<String,String>();
//                jsonObject.put(Constants.X_LOGIN_TOKEN,"123456");
//
//                mSocket.emit(Constants.CONNECTION_SERVER, jsonObject, new Ack() {
//                    @Override
//                    public void call(Object... args) {
//
//                        Log.e(TAG, "Constants.CONNECTION_SERVER ");
//
//                        int index = 0;
//                        while (index < args.length) {
//                            Log.e(TAG, "on ack connected" + index + "  " + args[index]);
//                            ++index;
//                        }
//
//                        MyApplication.getInstance().chatManager.onConnected();
//                    }
//                });
//            }
//
//        }).on("event", new Emitter.Listener() {
//
//            @Override
//            public void call(Object... args) {
//            }
//
//        }).on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {
//
//            @Override
//            public void call(Object... args) {
//            }
//
//        }).on(Socket.EVENT_CONNECT_ERROR, new Emitter.Listener() {
//            @Override
//            public void call(Object... args) {
//
//            }
//        }).on(Socket.EVENT_CONNECT_TIMEOUT, new Emitter.Listener() {
//            @Override
//            public void call(Object... args) {
//
//            }
//        });
//
//        mSocket.connect();


        MyApplication.getInstance().chatManager.onConnected();
    }

    /**
     * Check if socket is connected to server or has been down
     *
     * @return
     */
    public boolean isSocketConnected() {
        if (mSocket.connected())
            return true;
        else
            return false;
    }

    public void disconnectSocket() {
        if (mSocket != null)
            mSocket.disconnect();
    }


    public interface SocketManagerCallBack {
        void onConnected();
    }

}
