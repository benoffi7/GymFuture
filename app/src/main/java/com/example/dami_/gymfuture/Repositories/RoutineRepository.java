package com.example.dami_.gymfuture.Repositories;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.dami_.gymfuture.Database.DatabaseApp;
import com.example.dami_.gymfuture.Model.Routine;

import java.util.List;

public class RoutineRepository{
    private final LiveData<List<Routine>> list;
    private DatabaseApp databaseApp;

    public RoutineRepository(Application application){
        databaseApp = DatabaseApp.getDatabase(application);
        list = databaseApp.routineDao().getAll();

    }

    public LiveData<List<Routine>> getList() {
        return list;
    }

    public void delete(Routine routine){
        new deleteAsyncTask(databaseApp).execute(routine);
    }

    public void insert(Routine routine){
        new insertAsyncTask(databaseApp).execute(routine);
    }

    public void update(Routine routine){
        new updateAsyncTask(databaseApp).execute(routine);
    }

    public void deleteAll(){
        new deleteAllAsyncTask(databaseApp).execute();
    }

    private static class insertAsyncTask extends  AsyncTask<Routine, Void, Void>{

        private DatabaseApp db;

        insertAsyncTask(DatabaseApp databaseApp){
            db = databaseApp;
        }


        @Override
        protected Void doInBackground(Routine... routines) {
            db.routineDao().insert(routines);
            return null;
        }

    }
    private static class deleteAsyncTask extends AsyncTask<Routine, Void, Void> {

        private DatabaseApp db;

        deleteAsyncTask(DatabaseApp databaseApp) {
            db = databaseApp;
        }

        @Override
        protected Void doInBackground(final Routine... params) {
            db.routineDao().delete(params[0]);
            return null;
        }

    }

    private static class updateAsyncTask extends AsyncTask<Routine, Void, Void> {

        private DatabaseApp db;

        updateAsyncTask(DatabaseApp databaseApp) {
            db = databaseApp;
        }

        @Override
        protected Void doInBackground(final Routine... params) {
            db.routineDao().update(params[0]);
            return null;
        }

    }

    private static class deleteAllAsyncTask extends AsyncTask<Void, Void, Void> {

        private DatabaseApp db;

        deleteAllAsyncTask(DatabaseApp databaseApp) {
            db = databaseApp;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            db.routineDao().truncateTable();
            return null;
        }

    }
}
