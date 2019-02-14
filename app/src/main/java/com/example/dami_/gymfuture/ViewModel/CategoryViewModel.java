package com.example.dami_.gymfuture.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.dami_.gymfuture.Model.Category;
import com.example.dami_.gymfuture.Repositories.CategoryRepository;

import java.util.List;

public class CategoryViewModel extends AndroidViewModel {
    private CategoryRepository repository;
    private LiveData<List<Category>> list;

    public CategoryViewModel(@NonNull Application application){
        super(application);
        repository = new CategoryRepository(application);
        list = repository.getList();
    }

    public void insert(Category category){
        repository.insert(category);
    }

    public void update(Category category){
        repository.update(category);
    }

    public void delete(Category category){
        repository.delete(category);
    }

    public void deleteAll(){
        repository.deleteAll();
    }

    public LiveData<List<Category>> getAll(){
        return list;
    }
}
