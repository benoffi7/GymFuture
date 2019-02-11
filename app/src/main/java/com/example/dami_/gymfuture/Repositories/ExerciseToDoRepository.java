package com.example.dami_.gymfuture.Repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.dami_.gymfuture.Database.DatabaseApp;
import com.example.dami_.gymfuture.Model.ExerciseToDo;

import java.util.List;

public class ExerciseToDoRepository {
    private final LiveData<List<ExerciseToDo>> list;
    private DatabaseApp databaseApp;

    public ExerciseToDoRepository(Application application){
        databaseApp = DatabaseApp.getDatabase(application);
        list = databaseApp.exerciseToDoDao().getAll();

    }

    public LiveData<List<ExerciseToDo>> getList() {
        return list;
    }

    public void delete(ExerciseToDo exerciseToDo){
        new deleteAsyncTask(databaseApp).execute(exerciseToDo);
    }

    public void insert(ExerciseToDo exerciseToDo){
        new insertAsyncTask(databaseApp).execute(exerciseToDo);
    }

    public void update(ExerciseToDo exerciseToDo){
        new updateAsyncTask(databaseApp).execute(exerciseToDo);
    }

    public void deleteAll(){
        new deleteAllAsyncTask(databaseApp).execute();
    }

    private static class updateAsyncTask extends AsyncTask<ExerciseToDo, Void, Void> {

        private DatabaseApp db;

        updateAsyncTask(DatabaseApp databaseApp){
            db = databaseApp;
        }


        @Override
        protected Void doInBackground(ExerciseToDo... exerciseToDo) {
            db.exerciseToDoDao().update(exerciseToDo);
            return null;
        }

    }

    private static class deleteAllAsyncTask extends AsyncTask<Void, Void, Void> {

        private DatabaseApp db;

        deleteAllAsyncTask(DatabaseApp databaseApp){
            db = databaseApp;
        }


        @Override
        protected Void doInBackground(Void... voids) {
            db.exerciseToDoDao().truncateTable();
            return null;
        }

    }

    private static class insertAsyncTask extends AsyncTask<ExerciseToDo, Void, Void> {

        private DatabaseApp db;

        insertAsyncTask(DatabaseApp databaseApp){
            db = databaseApp;
        }


        @Override
        protected Void doInBackground(ExerciseToDo... exerciseToDo) {
            db.exerciseToDoDao().insert(exerciseToDo);
            return null;
        }

    }
    private static class deleteAsyncTask extends AsyncTask<ExerciseToDo, Void, Void> {

        private DatabaseApp db;

        deleteAsyncTask(DatabaseApp databaseApp) {
            db = databaseApp;
        }

        @Override
        protected Void doInBackground(final ExerciseToDo... params) {
            db.exerciseToDoDao().delete(params[0]);
            return null;
        }

    }
}
