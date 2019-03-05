package com.example.dami_.gymfuture.Dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.dami_.gymfuture.Model.Exercise;

import java.util.List;

@Dao
public interface ExerciseDao {
    @Insert
    void insert(Exercise... exercises);

    @Query("SELECT * FROM exercises")
    LiveData<List<Exercise>> getAll();

    @Query("DELETE FROM exercises")
    void truncateTable();

    @Delete
    void delete(Exercise... exercises);

}
