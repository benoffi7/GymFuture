package com.example.dami_.gymfuture.Dao;

import android.arch.lifecycle.LiveData;
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
    void insert(ExerciseToDo... exerciseToDo);
    @Delete
    void delete(ExerciseToDo... exerciseToDo);
    @Update
    void update(ExerciseToDo... exerciseToDo);
    @Query("SELECT * FROM exercisesToDo")
    LiveData<List<ExerciseToDo>> getAll();
    @Query("DELETE FROM exercisesToDo")
    void truncateTable();

}
