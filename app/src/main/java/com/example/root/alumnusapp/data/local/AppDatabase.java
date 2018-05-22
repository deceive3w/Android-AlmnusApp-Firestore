package com.example.root.alumnusapp.data.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.root.alumnusapp.data.model.User;

@Database(entities = {User.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
    static AppDatabase instance;
    public static AppDatabase get(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),AppDatabase.class,"user-db")
                    .allowMainThreadQueries().fallbackToDestructiveMigration().build();

        }
        return instance;
    }
}
