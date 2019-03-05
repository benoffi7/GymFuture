package com.example.dami_.gymfuture.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.dami_.gymfuture.Model.Routine;
import com.example.dami_.gymfuture.Repositories.RoutineRepository;

import java.util.List;

public class RoutineViewModel extends AndroidViewModel {
    private RoutineRepository repository;
    private LiveData<List<Routine>> list;

    public RoutineViewModel(@NonNull Application application){
        super(application);
        repository = new RoutineRepository(application);
        list = repository.getList();
    }

    public void insert(Routine routine){
        repository.insert(routine);
    }

    public void update(Routine routine){
        repository.update(routine);
    }

    public void delete(Routine routine){
        repository.delete(routine);
    }

    public void deleteAll(){
        repository.deleteAll();
    }

    public LiveData<List<Routine>> getAll(){
        return list;
    }

    public LiveData<List<Routine>> getByCategory(String id){
        return repository.getByCategory(id);
    }
}
