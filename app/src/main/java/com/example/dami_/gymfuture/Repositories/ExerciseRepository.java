package com.example.dami_.gymfuture.Repositories;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.dami_.gymfuture.Database.DatabaseApp;
import com.example.dami_.gymfuture.Model.Exercise;

import java.util.List;

public class ExerciseRepository {
    private final LiveData<List<Exercise>> listExercises;
    private DatabaseApp databaseApp;

    public ExerciseRepository(Application application){
        databaseApp = DatabaseApp.getDatabase(application);
        listExercises = databaseApp.exerciseDao().getAll();

    }

    public LiveData<List<Exercise>> getListExercises() {
        return listExercises;
    }

    public void delete(Exercise exercise){
        new deleteAsyncTask(databaseApp).execute(exercise);
    }

    public void insert(Exercise exercise){
        new insertAsyncTask(databaseApp).execute(exercise);
    }

    private static class insertAsyncTask extends  AsyncTask<Exercise, Void, Void>{

        private DatabaseApp db;

        insertAsyncTask(DatabaseApp databaseApp){
            db = databaseApp;
        }


        @Override
        protected Void doInBackground(Exercise... exercises) {
            db.exerciseDao().insert(exercises);
            return null;
        }

    }
    private static class deleteAsyncTask extends AsyncTask<Exercise, Void, Void> {

        private DatabaseApp db;

        deleteAsyncTask(DatabaseApp databaseApp) {
            db = databaseApp;
        }

        @Override
        protected Void doInBackground(final Exercise... params) {
            db.exerciseDao().delete(params[0]);
            return null;
        }

    }
}
