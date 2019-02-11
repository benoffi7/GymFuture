package com.example.dami_.gymfuture.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.dami_.gymfuture.Model.Objetive;
import com.example.dami_.gymfuture.Repositories.ObjetiveRepository;

import java.util.List;

public class ObjetiveViewModel extends AndroidViewModel {
    private ObjetiveRepository repository;
    private LiveData<List<Objetive>> list;

    public ObjetiveViewModel(@NonNull Application application){
        super(application);
        repository = new ObjetiveRepository(application);
        list = repository.getList();
    }

    public void insert(Objetive objetive){
        repository.insert(objetive);
    }

    public void update(Objetive objetive){
        repository.update(objetive);
    }

    public void delete(Objetive objetive){
        repository.delete(objetive);
    }

    public void deleteAll(){
        repository.deleteAll();
    }

    public Objetive getByName(String name) {
        Objetive obj = null;
        List<Objetive> list = this.list.getValue();

        if(list != null) {
            for (Objetive objetive : list) {
                if (objetive.getName().equals(name)) {
                    obj = objetive;
                    break;
                }
            }
        }

        return obj;
    }

    public LiveData<List<Objetive>> getAll(){
        return list;
    }

}
