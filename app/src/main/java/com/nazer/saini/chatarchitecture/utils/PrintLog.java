package com.nazer.saini.chatarchitecture.utils;

import android.util.Log;

import com.nazer.saini.chatarchitecture.BuildConfig;


public class PrintLog {

    private static boolean isDebug = BuildConfig.DEBUG;

    public static void e(String TAG, String msg) {
        if (isDebug)
            Log.e(TAG, msg);
    }

    public static void i(String TAG, String msg) {
        if (isDebug)
            Log.i(TAG, msg);
    }
    public static void d(String TAG, String msg) {
        if (isDebug)
            Log.d(TAG, msg);
    }
}
