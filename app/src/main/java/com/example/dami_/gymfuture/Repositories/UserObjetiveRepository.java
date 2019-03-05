package com.example.dami_.gymfuture.Repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.dami_.gymfuture.Database.DatabaseApp;
import com.example.dami_.gymfuture.Model.UserObjetive;


public class UserObjetiveRepository {
    private final LiveData<UserObjetive> userObjetive;
    private DatabaseApp databaseApp;

    public UserObjetiveRepository(Application application){
        this.databaseApp = DatabaseApp.getDatabase(application);
        this.userObjetive = databaseApp.userObjetiveDao().get();
    }

    public LiveData<UserObjetive> getUserObjetive() {
        return userObjetive;
    }

    public void delete(UserObjetive userObjetive){
        new deleteAsyncTask(databaseApp).execute(userObjetive);
    }

    public void insert(UserObjetive userObjetive){
        new insertAsyncTask(databaseApp).execute(userObjetive);
    }

    public void update(UserObjetive userObjetive) { new updateAsyncTask(databaseApp).execute(userObjetive);}

    public void deleteAll() { new deleteAllAsyncTask(databaseApp).execute(); }

    private static class deleteAllAsyncTask extends AsyncTask<Void, Void, Void> {

        private DatabaseApp db;

        deleteAllAsyncTask(DatabaseApp databaseApp){
            db = databaseApp;
        }


        @Override
        protected Void doInBackground(Void... voids) {
            db.userObjetiveDao().deleteAll();
            return null;
        }

    }

    private static class updateAsyncTask extends AsyncTask<UserObjetive, Void, Void> {

        private DatabaseApp db;

        updateAsyncTask(DatabaseApp databaseApp){
            db = databaseApp;
        }


        @Override
        protected Void doInBackground(UserObjetive... userObjetive) {
            db.userObjetiveDao().update(userObjetive);
            return null;
        }

    }

    private static class insertAsyncTask extends AsyncTask<UserObjetive, Void, Void> {

        private DatabaseApp db;

        insertAsyncTask(DatabaseApp databaseApp){
            db = databaseApp;
        }


        @Override
        protected Void doInBackground(UserObjetive... userObjetive) {
            System.out.println("USER: " + userObjetive[0].getId());
            db.userObjetiveDao().insert(userObjetive[0]);
            return null;
        }

    }
    private static class deleteAsyncTask extends AsyncTask<UserObjetive, Void, Void> {

        private DatabaseApp db;

        deleteAsyncTask(DatabaseApp databaseApp) {
            db = databaseApp;
        }

        @Override
        protected Void doInBackground(final UserObjetive... params) {
            db.userObjetiveDao().delete(params[0]);
            return null;
        }

    }
}