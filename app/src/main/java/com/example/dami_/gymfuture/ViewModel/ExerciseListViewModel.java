package com.example.dami_.gymfuture.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.dami_.gymfuture.Database.DatabaseApp;
import com.example.dami_.gymfuture.Model.Exercise;

import java.util.List;

public class ExerciseListViewModel extends AndroidViewModel {
    private final LiveData<List<Exercise>> listExercises;
    private DatabaseApp databaseApp;

    public ExerciseListViewModel(Application application){
        super(application);
        databaseApp = DatabaseApp.getDatabase(this.getApplication());
        listExercises = databaseApp.exerciseDao().getAll();

    }

    public LiveData<List<Exercise>> getListExercises() {
        return listExercises;
    }

    public void delete(Exercise exercise){
        new deleteAsyncTask(databaseApp).execute(exercise);
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
