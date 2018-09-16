package com.nazer.saini.chatarchitecture.managers.chatclients;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.nazer.saini.chatarchitecture.pojomodels.basemodels.ChatMessage;

import java.util.List;

@Dao
public interface ChatDatabaseDao {

    @Query("SELECT * FROM ChatMessage")
    List<ChatMessage> getAll();

//    @Query("SELECT * FROM ChatMessage where first_name LIKE  :firstName AND last_name LIKE :lastName")
//    ChatMessage findByName(String firstName, String lastName);

    @Query("SELECT COUNT(*) from ChatMessage")
    int countUsers();

    @Insert
    void insertAll(ChatMessage...chatMessages);


    @Insert
    long insert(ChatMessage chatMessages);

    @Query("UPDATE ChatMessage SET isLocalMessage = :isLocal  WHERE uid = :id")
    void update(int isLocal,long id);

    @Delete
    void delete(ChatMessage user);
}
