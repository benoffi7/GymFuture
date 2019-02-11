package com.example.dami_.gymfuture.Database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

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
, version = 5 , exportSchema = false)
public abstract class DatabaseApp extends RoomDatabase {

    private static DatabaseApp INSTANCE;

    public abstract ExerciseDao exerciseDao();
    public abstract RoutineDao routineDao();
    public abstract ObjetiveDao objetiveDao();
    public abstract DayDao dayDao();
    public abstract ExerciseToDoDao exerciseToDoDao();

    public static synchronized DatabaseApp getDatabase(Context context){
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                         DatabaseApp.class,
                        "gym_future_db")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(INSTANCE).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private ExerciseDao exerciseDao;

        private PopulateDbAsyncTask(DatabaseApp databaseApp){
            exerciseDao = databaseApp.exerciseDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
    }
}
