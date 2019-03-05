package com.example.dami_.gymfuture.Database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.example.dami_.gymfuture.Dao.CategoryDao;
import com.example.dami_.gymfuture.Dao.DayDao;
import com.example.dami_.gymfuture.Dao.ExerciseDao;
import com.example.dami_.gymfuture.Dao.ExerciseToDoDao;
import com.example.dami_.gymfuture.Dao.ObjetiveDao;
import com.example.dami_.gymfuture.Dao.RoutineDao;
import com.example.dami_.gymfuture.Dao.UserObjetiveDao;
import com.example.dami_.gymfuture.Model.Category;
import com.example.dami_.gymfuture.Model.Day;
import com.example.dami_.gymfuture.Model.Exercise;
import com.example.dami_.gymfuture.Model.ExerciseToDo;
import com.example.dami_.gymfuture.Model.Objetive;
import com.example.dami_.gymfuture.Model.Routine;
import com.example.dami_.gymfuture.Model.UserObjetive;

@Database(entities = {Exercise.class, Routine.class, Day.class, ExerciseToDo.class, Objetive.class ,
        Category.class, UserObjetive.class}
, version = 14 , exportSchema = false)
public abstract class DatabaseApp extends RoomDatabase {

    private static DatabaseApp INSTANCE;

    public abstract ExerciseDao exerciseDao();
    public abstract RoutineDao routineDao();
    public abstract ObjetiveDao objetiveDao();
    public abstract DayDao dayDao();
    public abstract ExerciseToDoDao exerciseToDoDao();
    public abstract CategoryDao categoryDao();
    public abstract UserObjetiveDao userObjetiveDao();

    public static synchronized DatabaseApp getDatabase(Context context){
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                         DatabaseApp.class,
                        "gym_future_db")
                    .fallbackToDestructiveMigration()
                   // .addCallback(roomCallback)
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
        private UserObjetiveDao userObjetiveDao;

        private PopulateDbAsyncTask(DatabaseApp databaseApp){
            userObjetiveDao = databaseApp.userObjetiveDao();
        }

        @Override
        protected Void doInBackground(Void... voids)
        {
            return null;

        }
    }
}
