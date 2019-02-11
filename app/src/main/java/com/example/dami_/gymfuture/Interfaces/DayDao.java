package com.example.dami_.gymfuture.Interfaces;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.dami_.gymfuture.Model.Day;

import java.util.List;

@Dao
public interface DayDao {
    @Insert
    void insert(Day... days);
    @Delete
    void delete(Day... days);
    @Update
    void update(Day... days);
    @Query("SELECT * FROM days")
    LiveData<List<Day>> getAll();
    @Query("DELETE FROM days")
    void truncateTable();
}
