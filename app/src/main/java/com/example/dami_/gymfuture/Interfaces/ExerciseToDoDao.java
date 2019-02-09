package com.example.dami_.gymfuture.Interfaces;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.dami_.gymfuture.Model.ExerciseToDo;

import java.util.List;

@Dao
public interface ExerciseToDoDao {
    @Insert
    void add(ExerciseToDo... exerciseToDo);
    @Delete
    void delete(ExerciseToDo... exerciseToDo);
    @Update
    void update(ExerciseToDo... exerciseToDo);
    @Query("SELECT * FROM exercisesToDo")
    List<ExerciseToDo> getAll();
    @Query("DELETE FROM exercisesToDo")
    void truncateTable();

}
