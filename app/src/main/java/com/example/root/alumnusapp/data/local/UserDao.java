package com.example.root.alumnusapp.data.local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.root.alumnusapp.data.model.User;

import java.util.List;

@Dao
public interface UserDao {
    @Query("Select * from user limit 1")
    User getUser();

    @Insert
    void insert(User user);

    @Delete
    void delete(User user);
}
