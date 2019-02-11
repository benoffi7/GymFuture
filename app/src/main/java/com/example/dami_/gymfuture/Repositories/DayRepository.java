package com.example.dami_.gymfuture.Repositories;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.dami_.gymfuture.Database.DatabaseApp;
import com.example.dami_.gymfuture.Model.Day;

import java.util.List;

public class DayRepository {
    private final LiveData<List<Day>> list;
    private DatabaseApp databaseApp;

    public DayRepository(Application application){
        this.databaseApp = DatabaseApp.getDatabase(application);
        this.list = databaseApp.dayDao().getAll();
    }

    public LiveData<List<Day>> getList() {
        return list;
    }

    public void delete(Day day){
        new deleteAsyncTask(databaseApp).execute(day);
    }

    public void insert(Day day){
        new insertAsyncTask(databaseApp).execute(day);
    }

    public void update(Day day) { new updateAsyncTask(databaseApp).execute(day);}

    public void deleteAll() { new deleteAllAsyncTask(databaseApp).execute(); }

    private static class deleteAllAsyncTask extends AsyncTask<Void, Void, Void> {

        private DatabaseApp db;

        deleteAllAsyncTask(DatabaseApp databaseApp){
            db = databaseApp;
        }


        @Override
        protected Void doInBackground(Void... voids) {
            db.dayDao().truncateTable();
            return null;
        }

    }

    private static class updateAsyncTask extends AsyncTask<Day, Void, Void> {

        private DatabaseApp db;

        updateAsyncTask(DatabaseApp databaseApp){
            db = databaseApp;
        }


        @Override
        protected Void doInBackground(Day... days) {
            db.dayDao().update(days);
            return null;
        }

    }

    private static class insertAsyncTask extends AsyncTask<Day, Void, Void> {

        private DatabaseApp db;

        insertAsyncTask(DatabaseApp databaseApp){
            db = databaseApp;
        }


        @Override
        protected Void doInBackground(Day... days) {
            db.dayDao().insert(days);
            return null;
        }

    }
    private static class deleteAsyncTask extends AsyncTask<Day, Void, Void> {

        private DatabaseApp db;

        deleteAsyncTask(DatabaseApp databaseApp) {
            db = databaseApp;
        }

        @Override
        protected Void doInBackground(final Day... params) {
            db.dayDao().delete(params[0]);
            return null;
        }

    }
}
