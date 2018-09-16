package com.nazer.saini.chatarchitecture.utils;

import android.content.Context;
import android.os.Environment;
import android.webkit.MimeTypeMap;

import com.nazer.saini.chatarchitecture.pojomodels.ChatMediaType;

import java.io.File;
import java.text.DecimalFormat;

public class Utility {

    /**
     * Get file mimetype from file extension
     */
    public static String getFileMimeType(String filePath) {
        // Get file extension from file path
        String fileExtension = filePath.substring(filePath.lastIndexOf(".") + 1);
        // Get file mime type from extension
        String mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(fileExtension);
        if (mimeType == null) {
            // Invalid file
            return null;
        }
        return mimeType;
    }

    /**
     * create directory for chat images in storage directory
     * @param messageType
     */
    public static File saveDownloadedMedia(ChatMediaType messageType) {
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), Constants.DIRECTORY_NAME);
        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            mediaStorageDir.mkdirs();
        }
        File mediaFile = null;
        switch (messageType){
            case IMAGE_TEXT:
                mediaFile = new File(mediaStorageDir.getPath() + File.separator + "IMG_" + ".jpg");
                break;
            case VIDEO_TEXT:
                mediaFile = new File(mediaStorageDir.getPath() + File.separator + "VID_" + ".mp4");
                break;
        }
        return mediaFile;
    }



    public static boolean isOnline(Context context)
    {
        return true;
    }


    /**
     * Get the file size in a human-readable string.
     *
     * @param size
     * @return
     * @author paulburke
     */
    public static String getReadableFileSize(int size) {
        final int BYTES_IN_KILOBYTES = 1024;
        final DecimalFormat dec = new DecimalFormat("###.#");
        final String KILOBYTES = " KB";
        final String MEGABYTES = " MB";
        final String GIGABYTES = " GB";
        float fileSize = 0;
        String suffix = KILOBYTES;

        if (size > BYTES_IN_KILOBYTES) {
            fileSize = size / BYTES_IN_KILOBYTES;
            if (fileSize > BYTES_IN_KILOBYTES) {
                fileSize = fileSize / BYTES_IN_KILOBYTES;
                if (fileSize > BYTES_IN_KILOBYTES) {
                    fileSize = fileSize / BYTES_IN_KILOBYTES;
                    suffix = GIGABYTES;
                } else {
                    suffix = MEGABYTES;
                }
            }
        }
        return String.valueOf(dec.format(fileSize) + suffix);
    }

}
