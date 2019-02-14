package com.example.dami_.gymfuture.Repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.dami_.gymfuture.Database.DatabaseApp;
import com.example.dami_.gymfuture.Model.Category;

import java.util.List;

public class CategoryRepository {
    private final LiveData<List<Category>> list;
    private DatabaseApp databaseApp;

    public CategoryRepository(Application application){
        this.databaseApp = DatabaseApp.getDatabase(application);
        this.list = databaseApp.categoryDao().getAll();
    }

    public LiveData<List<Category>> getList() {
        return list;
    }

    public void delete(Category category){
        new deleteAsyncTask(databaseApp).execute(category);
    }

    public void insert(Category category){
        new insertAsyncTask(databaseApp).execute(category);
    }

    public void update(Category category) { new updateAsyncTask(databaseApp).execute(category);}

    public void deleteAll() { new deleteAllAsyncTask(databaseApp).execute(); }

    private static class deleteAllAsyncTask extends AsyncTask<Void, Void, Void> {

        private DatabaseApp db;

        deleteAllAsyncTask(DatabaseApp databaseApp){
            db = databaseApp;
        }


        @Override
        protected Void doInBackground(Void... voids) {
            db.categoryDao().truncate();
            return null;
        }

    }

    private static class updateAsyncTask extends AsyncTask<Category, Void, Void> {

        private DatabaseApp db;

        updateAsyncTask(DatabaseApp databaseApp){
            db = databaseApp;
        }


        @Override
        protected Void doInBackground(Category... categories) {
            db.categoryDao().update(categories);
            return null;
        }

    }

    private static class insertAsyncTask extends AsyncTask<Category, Void, Void> {

        private DatabaseApp db;

        insertAsyncTask(DatabaseApp databaseApp){
            db = databaseApp;
        }


        @Override
        protected Void doInBackground(Category... categories) {
            db.categoryDao().insert(categories);
            return null;
        }

    }
    private static class deleteAsyncTask extends AsyncTask<Category, Void, Void> {

        private DatabaseApp db;

        deleteAsyncTask(DatabaseApp databaseApp) {
            db = databaseApp;
        }

        @Override
        protected Void doInBackground(final Category... params) {
            db.categoryDao().delete(params[0]);
            return null;
        }

    }
}
