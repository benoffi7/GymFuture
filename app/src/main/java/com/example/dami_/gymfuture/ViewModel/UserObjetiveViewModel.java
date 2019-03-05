package com.example.dami_.gymfuture.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.dami_.gymfuture.Model.UserObjetive;
import com.example.dami_.gymfuture.Repositories.UserObjetiveRepository;


public class UserObjetiveViewModel extends AndroidViewModel {
    private LiveData<UserObjetive> userObjetiveLiveData;
    private UserObjetiveRepository repository;

    public UserObjetiveViewModel(@NonNull Application application){
        super(application);
        repository = new UserObjetiveRepository(application);
        userObjetiveLiveData = repository.getUserObjetive();
    }

    public void insert(UserObjetive userObjetive){
        repository.insert(userObjetive);
    }

    public void update(UserObjetive userObjetive){
        repository.update(userObjetive);
    }

    public void delete(UserObjetive userObjetive){
        repository.delete(userObjetive);
    }

    public void deleteAll(){
        repository.deleteAll();
    }

    public LiveData<UserObjetive> get(){
        return userObjetiveLiveData;
    }
}
