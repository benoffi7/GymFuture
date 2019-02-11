package com.example.dami_.gymfuture.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.dami_.gymfuture.Model.Day;
import com.example.dami_.gymfuture.Repositories.DayRepository;

import java.util.List;

public class DayViewModel extends AndroidViewModel {
    private DayRepository repository;
    private LiveData<List<Day>> list;

    public DayViewModel(@NonNull Application application){
        super(application);
        repository = new DayRepository(application);
        list = repository.getList();
    }

    public void insert(Day day){
        repository.insert(day);
    }

    public void update(Day day){
        repository.update(day);
    }

    public void delete(Day day){
        repository.delete(day);
    }

    public void deleteAll(){
        repository.deleteAll();
    }

    public LiveData<List<Day>> getAll(){
        return list;
    }
}
