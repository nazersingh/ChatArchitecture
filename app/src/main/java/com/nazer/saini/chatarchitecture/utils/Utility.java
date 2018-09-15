package com.nazer.saini.chatarchitecture.utils;

import android.os.Environment;
import android.webkit.MimeTypeMap;

import com.nazer.saini.chatarchitecture.pojomodels.ChatMediaType;

import java.io.File;

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
}
