package com.nazer.saini.chatarchitecture.ui.dialogues;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.DatePicker;


import com.nazer.saini.chatarchitecture.utils.ImageVideoAudioPicker;

import java.util.Calendar;
import java.util.TimeZone;


public class Dialogues {


    private static ProgressDialog progressDialog;

    private static Dialogues dialogues;

    private String title;
    private String msg;
    private String mPositiveButton;
    private String mNegativeButton;
    private boolean isCancelable;
    AlertDialog.Builder builder;


    public static Dialogues getInstance() {
        if (dialogues == null)
            dialogues = new Dialogues();
        return dialogues;
    }

    private Dialogues() {
    }

    /**
     * set title for alert Dialogue
     *
     * @param title
     * @return
     */
    public Dialogues setTitle(String title) {
        this.title = title;
        return this;
    }

    /**
     * set message to show on Alert Dialogue Box
     *
     * @param message
     * @return
     */
    public Dialogues setMsg(String message) {
        this.msg = message;
        return this;
    }


    public Dialogues setPositiveButton(String text) {
        this.mPositiveButton = text;
        return this;
    }

    public Dialogues setNegativeButton(String text) {
        this.mNegativeButton = text;
        return this;
    }


    public static void showLoader(Context context) {
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loading..");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    public static void dissmissDialogue() {
        if (progressDialog != null && progressDialog.isShowing())
            progressDialog.dismiss();
    }

    /**
     * @param context
     * @return
     */
    private AlertDialog.Builder getAlertDialogue(Context context) {

        if (builder == null)
            builder = new AlertDialog.Builder(context);
        return builder;
    }

    /**
     * ======================================= getAlertDialogue Instance ==============================================
     *
     * @param context
     * @param style
     * @return
     */
    private AlertDialog.Builder getAlertDialogue(Context context, int style) {
        if (builder == null)
            builder = new AlertDialog.Builder(context, style);
        return builder;
    }

    /**
     * ================================================== Show Alert Message getInstance Button ===================================
     * on Buttons click Perform Action using callback
     *
     * @param context
     * @param dialogueTwoButtonCallback
     */
    public void messageWithTwoButton(Context context, final DialogueTwoButtonCallback<String> dialogueTwoButtonCallback) {
        getAlertDialogue(context)
                .setTitle(title)
                .setMessage(msg)
                .setCancelable(isCancelable)
                .setPositiveButton(mPositiveButton, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialogueTwoButtonCallback.OnOkClick("");
                    }
                })
                .setNegativeButton(mNegativeButton, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialogueTwoButtonCallback.OnCancelClick("");
                    }
                });
        builder.show();
    }

    /**
     * ====================================================Show Alert Message getInstance Button===================================
     * on Button click Perform Action using callback
     *
     * @param context
     * @param dialogueOneButtonCallback
     */
    public void messageWithOneButton(Context context, final DialogueOneButtonCallback<String> dialogueOneButtonCallback) {
        getAlertDialogue(context)
                .setTitle(title)
                .setMessage(msg)
                .setCancelable(isCancelable)
                .setPositiveButton(mPositiveButton, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialogueOneButtonCallback.OnOkClick("");
                    }
                }).show();
    }

    /**
     * ================================================ Message Only =============================
     * Show Only Alert Message
     *
     * @param context
     */
    public void messageOnly(Context context) {
        getAlertDialogue(context)
                .setTitle(title)
                .setMessage(msg)
                .setPositiveButton(mPositiveButton, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();
    }

    /**
     * ================================ Dialogues for Media Picker ==========================
     */

    /**
     * Image Picker
     *
     * @param context
     */
    public void showImagePickerDialogue(final Activity context) {
        getAlertDialogue(context)
                .setTitle("Select Option")
                .setMessage("Select Image From Gallery\nSelect Image from camera")
                .setCancelable(isCancelable)
                .setPositiveButton("Gallery", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ImageVideoAudioPicker.getInstance().pickImageFromGalleryIntent(context);
                    }
                })
                .setNegativeButton("Camera", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ImageVideoAudioPicker.getInstance().pickImageFromCameraIntent(context);
                    }
                }).show();

    }

    /**
     * =============================================== Video Picker
     *
     * @param context
     */
    public void showVideoPickerDialogue(final Activity context) {
        getAlertDialogue(context)
                .setTitle("Select Option")
                .setMessage("Select Video From Gallery\nSelect Video from camera")
                .setCancelable(isCancelable)
                .setPositiveButton("Gallery", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ImageVideoAudioPicker.getInstance().pickImageFromGalleryIntent(context);
                    }
                })
                .setNegativeButton("Camera", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ImageVideoAudioPicker.getInstance().pickImageFromCameraIntent(context);
                    }
                }).show();

    }

    public interface DialogueOneButtonCallback<T> {
        void OnOkClick(T t);
    }
    public interface DialogueTwoButtonCallback<T> {

        void OnOkClick(T t);

        void OnCancelClick(T t);

    }

}
