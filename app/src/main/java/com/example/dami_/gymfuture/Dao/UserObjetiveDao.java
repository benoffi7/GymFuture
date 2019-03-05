package com.example.dami_.gymfuture.Dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.dami_.gymfuture.Model.UserObjetive;

@Dao
public interface UserObjetiveDao {

    @Insert
    void insert(UserObjetive... objetives);
    @Delete
    void delete(UserObjetive... objetives);
    @Update
    void update(UserObjetive... objetives);
    @Query("SELECT * FROM user_objetive LIMIT 1")
    LiveData<UserObjetive> get();
    @Query("DELETE FROM user_objetive")
    void deleteAll();
}
