package com.example.dami_.gymfuture.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.dami_.gymfuture.Model.Exercise;
import com.example.dami_.gymfuture.Repositories.ExerciseRepository;

import java.util.List;

public class ExerciseViewModel extends AndroidViewModel {
    private ExerciseRepository repository;
    private LiveData<List<Exercise>> list;

    public ExerciseViewModel(@NonNull Application application){
        super(application);
        repository = new ExerciseRepository(application);
        list = repository.getListExercises();
    }

    public void insert(Exercise exercise){
        repository.insert(exercise);
    }

    public void delete(Exercise exercise){
        repository.delete(exercise);
    }

    public void deleteAll(){ repository.deleteAll(); }

    public LiveData<List<Exercise>> getAll(){
        return list;
    }
}
