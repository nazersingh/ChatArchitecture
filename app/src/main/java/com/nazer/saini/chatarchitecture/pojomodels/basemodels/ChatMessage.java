package com.nazer.saini.chatarchitecture.pojomodels.basemodels;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.nazer.saini.chatarchitecture.pojomodels.ChatMediaStatus;
import com.nazer.saini.chatarchitecture.pojomodels.ChatMediaType;

@Entity(tableName = "ChatMessage")
public class ChatMessage {

    @PrimaryKey(autoGenerate = true)
    private long uid;

    private String messageBody = "";
    public  int isLocalMessage = 1;

    public int getIsLocalMessage() {
        return isLocalMessage;
    }

    public void setIsLocalMessage(int isLocalMessage) {
        this.isLocalMessage = isLocalMessage;
    }

    public int getTempIsMessageTypeSend() {
        return tempIsMessageTypeSend;
    }

    public void setTempIsMessageTypeSend(int tempIsMessageTypeSend) {
        this.tempIsMessageTypeSend = tempIsMessageTypeSend;
    }

    private int tempIsMessageTypeSend;
    private long time = 0;
    private String roomId = "";
    private String localUrl = "";
    private String remoteUrl = "";
    private String caption = "";
    private String fileName = "";
    private String messageCaption = "";
    private int mediaProgress = 0;
    private String messageType ;
    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }



//    private String chatMediaStatus = String.valueOf(ChatMediaStatus.UNKNOWN);


//    public void setChatMediaStatus(String chatMediaStatus) {
//        this.chatMediaStatus = chatMediaStatus;
//    }
//public ChatMediaStatus getChatMediaStatus() {
//        return ChatMediaStatus.valueOf(chatMediaStatus);
//    }
//
//    public void setChatMediaStatus(ChatMediaStatus chatMediaStatus) {
//        this.chatMediaStatus = chatMediaStatus.toString();
//    }






    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

//
    public String getLocalUrl() {
        return localUrl;
    }

    public void setLocalUrl(String localUrl) {
        this.localUrl = localUrl;
    }

    public String getRemoteUrl() {
        return remoteUrl;
    }

    public void setRemoteUrl(String remoteUrl) {
        this.remoteUrl = remoteUrl;
    }




    public long getUid() {
        return uid;
    }

    public void setUid(long local_id) {
        this.uid = local_id;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getMessageCaption() {
        return messageCaption;
    }

    public void setMessageCaption(String messageCaption) {
        this.messageCaption = messageCaption;
    }

    public int getMediaProgress() {
        return mediaProgress;
    }

    public void setMediaProgress(int mediaProgress) {
        this.mediaProgress = mediaProgress;
    }
}
