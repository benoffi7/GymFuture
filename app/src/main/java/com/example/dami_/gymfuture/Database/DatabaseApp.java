package com.example.dami_.gymfuture.Database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.dami_.gymfuture.Interfaces.DaoExercise;

@Database(entities = {} , version = 1 , exportSchema = false)
public abstract class DatabaseApp extends RoomDatabase {
    public abstract DaoExercise daoExercise();
}
