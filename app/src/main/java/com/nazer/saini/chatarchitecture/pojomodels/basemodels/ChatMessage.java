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
    private boolean isLocalMessage = false;
    private boolean tempIsMessageTypeSend;
    private long time = 0;
    private String roomId = "";
    private String localUrl = "";
    private String remoteUrl = "";
    private String caption = "";
    private String fileName = "";
    private String messageCaption = "";
    private int mediaProgress = 0;
    private String chatMediaStatus = String.valueOf(ChatMediaStatus.UNKNOWN);
    private String messageType = String.valueOf(ChatMediaType.TEXT);

    public boolean isLocalMessage() {
        return isLocalMessage;
    }

    public void setLocalMessage(boolean localMessage) {
        isLocalMessage = localMessage;
    }

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

    public ChatMediaStatus getChatMediaStatus() {
        return ChatMediaStatus.valueOf(chatMediaStatus);
    }

    public void setChatMediaStatus(ChatMediaStatus chatMediaStatus) {
        this.chatMediaStatus = chatMediaStatus.toString();
    }

    public ChatMediaType getMessageType() {
        return ChatMediaType.valueOf(messageType);
    }

    public void setMessageType(ChatMediaType messageType) {
        this.messageType = messageType.toString();
    }

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

    public boolean getTempIsMessageTypeSend() {
        return tempIsMessageTypeSend;
    }

    public void setTempIsMessageTypeSend(boolean tempIsMessageTypeSend) {
        this.tempIsMessageTypeSend = tempIsMessageTypeSend;
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
