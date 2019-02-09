package com.example.dami_.gymfuture.Interfaces;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.dami_.gymfuture.Model.Exercise;

import java.util.List;

@Dao
public interface ExerciseDao {
    @Insert
    void addExercise(Exercise exercise);

    @Query("SELECT * FROM exercises")
    List<Exercise> getAll();

    @Query("DELETE FROM exercises")
    void truncateTable();

}
