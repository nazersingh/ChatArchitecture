package com.nazer.saini.chatarchitecture.managers.chatclients;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.nazer.saini.chatarchitecture.pojomodels.basemodels.ChatMessage;

@Database(entities = {ChatMessage.class}, version = 1)
public abstract class AppRoomDatabase extends RoomDatabase {


    private static AppRoomDatabase INSTANCE;

    public abstract ChatDatabaseDao chatDatabaseDao();

    public static AppRoomDatabase getAppDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppRoomDatabase.class, "Chat_Database")
                    // allow queries on the main thread.
                    // Don't do this on a real app! See PersistenceBasicSample for an example.
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

}
