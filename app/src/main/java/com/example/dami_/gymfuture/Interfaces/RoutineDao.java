package com.example.dami_.gymfuture.Interfaces;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.dami_.gymfuture.Model.Routine;

import java.util.List;

@Dao
public interface RoutineDao {

    @Delete
    void delete(Routine... routines);

    @Update
    void update(Routine... routines);

    @Insert
    void insert(Routine... routines);

    @Query("DELETE FROM routines")
    void truncateTable();

    @Query("SELECT * FROM routines")
    LiveData<List<Routine>> getAll();
}
