package com.example.dami_.gymfuture.Repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.dami_.gymfuture.Database.DatabaseApp;
import com.example.dami_.gymfuture.Model.Objetive;

import java.util.List;

public class ObjetiveRepository {
    private final LiveData<List<Objetive>> list;
    private DatabaseApp databaseApp;

    public ObjetiveRepository(Application application){
        databaseApp = DatabaseApp.getDatabase(application);
        list = databaseApp.objetiveDao().getAll();

    }

    public LiveData<List<Objetive>> getList() {
        return list;
    }

    public void delete(Objetive objetive){
        new deleteAsyncTask(databaseApp).execute(objetive);
    }

    public void insert(Objetive objetive){
        new insertAsyncTask(databaseApp).execute(objetive);
    }

    public void update(Objetive objetive){
        new updateAsyncTask(databaseApp).execute(objetive);
    }

    public void deleteAll(){
        new deleteAllAsyncTask(databaseApp).execute();
    }

    public Objetive getByName(String name){
        Objetive objetive = null;
        try{
            objetive = new getbyNameAsyncTask(databaseApp).execute(name).get();
        }catch (Exception ignored){}

        return objetive;
    }

    public Objetive getById(String id){
        Objetive objetive = null;
        try{
            objetive = new getbyIdAsyncTask(databaseApp).execute(id).get();
        }catch (Exception ignored){}

        return objetive;
    }


    private static class deleteAllAsyncTask extends AsyncTask<Void, Void, Void> {

        private DatabaseApp db;

        deleteAllAsyncTask(DatabaseApp databaseApp){
            db = databaseApp;
        }


        @Override
        protected Void doInBackground(Void... voids) {
            db.objetiveDao().truncateTable();
            return null;
        }

    }

    private static class updateAsyncTask extends AsyncTask<Objetive, Void, Void> {

        private DatabaseApp db;

        updateAsyncTask(DatabaseApp databaseApp){
            db = databaseApp;
        }


        @Override
        protected Void doInBackground(Objetive... objetives) {
            db.objetiveDao().update(objetives);
            return null;
        }

    }

    private static class insertAsyncTask extends AsyncTask<Objetive, Void, Void> {

        private DatabaseApp db;

        insertAsyncTask(DatabaseApp databaseApp){
            db = databaseApp;
        }


        @Override
        protected Void doInBackground(Objetive... objetives) {
            db.objetiveDao().insert(objetives);
            return null;
        }

    }
    private static class deleteAsyncTask extends AsyncTask<Objetive, Void, Void> {

        private DatabaseApp db;

        deleteAsyncTask(DatabaseApp databaseApp) {
            db = databaseApp;
        }

        @Override
        protected Void doInBackground(final Objetive... params) {
            db.objetiveDao().delete(params[0]);
            return null;
        }

    }

    private static class getbyNameAsyncTask extends AsyncTask<String, Void , Objetive> {

        private DatabaseApp db;

        getbyNameAsyncTask(DatabaseApp databaseApp) {
            db = databaseApp;
        }

        @Override
        protected Objetive doInBackground(final String... strings) {
           return  db.objetiveDao().getByName(strings[0]);
        }

    }

    private static class getbyIdAsyncTask extends AsyncTask<String, Void , Objetive> {

        private DatabaseApp db;

        getbyIdAsyncTask(DatabaseApp databaseApp) {
            db = databaseApp;
        }

        @Override
        protected Objetive doInBackground(final String... strings) {
            return  db.objetiveDao().getById(strings[0]);
        }

    }
}
