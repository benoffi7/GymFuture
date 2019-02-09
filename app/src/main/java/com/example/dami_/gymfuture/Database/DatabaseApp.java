package com.example.dami_.gymfuture.Database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.dami_.gymfuture.Interfaces.DayDao;
import com.example.dami_.gymfuture.Interfaces.ExerciseDao;
import com.example.dami_.gymfuture.Interfaces.ExerciseToDoDao;
import com.example.dami_.gymfuture.Interfaces.ObjetiveDao;
import com.example.dami_.gymfuture.Interfaces.RoutineDao;
import com.example.dami_.gymfuture.Model.Day;
import com.example.dami_.gymfuture.Model.Exercise;
import com.example.dami_.gymfuture.Model.ExerciseToDo;
import com.example.dami_.gymfuture.Model.Objetive;
import com.example.dami_.gymfuture.Model.Routine;

@Database(entities = {Exercise.class, Routine.class, Day.class, ExerciseToDo.class, Objetive.class}
, version = 4 , exportSchema = false)
public abstract class DatabaseApp extends RoomDatabase {
    public abstract ExerciseDao exerciseDao();
    public abstract RoutineDao routineDao();
    public abstract ObjetiveDao objetiveDao();
    public abstract DayDao dayDao();
    public abstract ExerciseToDoDao exerciseToDoDao();
}
