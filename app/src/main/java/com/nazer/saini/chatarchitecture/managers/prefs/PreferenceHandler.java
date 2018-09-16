package com.nazer.saini.chatarchitecture.managers.prefs;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * Common PrefrenceConnector class for storing preference values.
 * 
 */
public class PreferenceHandler {

	public static final String PREF_NAME = "APPFRAMEWORK_PREFERENCES";
	public static final String PREF_KEY_USER_VIEW_LOGIN = "PREF_KEY_USER_VIEW_LOGIN";
	public static final String PREF_KEY_USER_VIEW_LOGIN_PASSWORD = "PREF_KEY_USER_VIEW_LOGIN_PASSWORD";
	public static final String FCM_TOKEN = "FCM_TOKEN";
	public static final int MODE = Context.MODE_PRIVATE;
	public static final String PREF_KEY_LOGIN = "PREF_KEY_LOGIN";
	public static final String PREF_KEY_LOGIN_REMEMBER = "PREF_KEY_LOGIN_REMEMBER";
	public static final String PREF_KEY_USER_ID = "PREF_KEY_USER_ID";
	public static final String PREF_KEY_USER_NOTIFICATION_ON = "PREF_KEY_USER_NOTIFICATION_ON";
	public static final String PREF_KEY_USER_LOGIN_TYPE = "PREF_KEY_USER_LOGIN_TYPE";
	public static final String PREF_KEY_TOKEN = "PREF_KEY_TOKEN";
	public static final String PREF_KEY_USER_NAME = "PREF_KEY_USER_NAME";
	public static final String PREF_KEY_USER_EMAIL = "PREF_KEY_USER_EMAIL";
	public static final String PREF_KEY_USER_AUTHTOKEN = "PREF_KEY_USER_AUTHTOKEN";
	public static final String PREF_KEY_IS_USER_LOGIN="PREF_IS_USER_LOGIN";
	public static final String PREF_KEY_USER_PHONE ="PREF_KEY_USER_PHONE" ;
	public static final String PREF_KEY_LOGIN_TOKEN = "PREF_KEY_LOGIN_TOKEN";
	public static final String PREF_KEY_USER_IMAGE = "PREF_KEY_USER_IMAGE";

	public static final String PREF_KEY_EXCEPTION_ENABLE = "PREF_KEY_EXCEPTION_ENABLE";
	public static final String PREF_KEY_EXCEPTION_DIALOG_ENABLE = "PREF_KEY_EXCEPTION_DIALOG_ENABLE";

	public static final String PREF_KEY_SHOW_CREDENTIALS = "PREF_KEY_SHOW_CREDENTIALS";


    public static void writeBoolean(Context context, String key, boolean value) {
		getEditor(context).putBoolean(key, value).commit();
	}

	public static boolean readBoolean(Context context, String key,
			boolean defValue) {
		return getPreferences(context).getBoolean(key, defValue);
	}

	public static void writeInteger(Context context, String key, int value) {
		getEditor(context).putInt(key, value).commit();
	}

	public static int readInteger(Context context, String key, int defValue) {
		return getPreferences(context).getInt(key, defValue);
	}

	public static void writeString(Context context, String key, String value) {
		getEditor(context).putString(key, value).commit();
	}

	public static String readString(Context context, String key, String defValue) {
		return getPreferences(context).getString(key, defValue);
	}

	public static void writeFCM_KEY(Context context, String key, String value) {
		getFCMEditor(context).putString(key, value).commit();
	}

	public static String readFCM_KEY(Context context, String key, String defValue) {
		return getFCMPREFERENCE(context).getString(key, defValue);
	}

	public static void writeFloat(Context context, String key, float value) {
		getEditor(context).putFloat(key, value).commit();
	}

	public static float readFloat(Context context, String key, float defValue) {
		return getPreferences(context).getFloat(key, defValue);
	}

	public static void writeLong(Context context, String key, long value) {
		getEditor(context).putLong(key, value).commit();
	}

	public static long readLong(Context context, String key, long defValue) {
		return getPreferences(context).getLong(key, defValue);
	}

	public static SharedPreferences getPreferences(Context context) {
		return context.getSharedPreferences(PREF_NAME, MODE);
	}

	public static Editor getEditor(Context context) {
		return getPreferences(context).edit();
	}

	public static SharedPreferences getFCMPREFERENCE(Context context) {
		return context.getSharedPreferences(PREF_NAME, MODE);
	}

	public static Editor getFCMEditor(Context context) {
		return getFCMPREFERENCE(context).edit();
	}



}
