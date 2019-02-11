package com.example.dami_.gymfuture.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.dami_.gymfuture.Model.ExerciseToDo;
import com.example.dami_.gymfuture.Repositories.ExerciseToDoRepository;

import java.util.List;

public class ExerciseToDoViewModel extends AndroidViewModel {
        private ExerciseToDoRepository repository;
        private LiveData<List<ExerciseToDo>> list;

        public ExerciseToDoViewModel(@NonNull Application application){
            super(application);
            repository = new ExerciseToDoRepository(application);
            list = repository.getList();
        }

        public void insert(ExerciseToDo exerciseToDo){
            repository.insert(exerciseToDo);
        }

        public void update(ExerciseToDo exerciseToDo){
            repository.update(exerciseToDo);
        }

        public void delete(ExerciseToDo exerciseToDo){
            repository.delete(exerciseToDo);
        }

        public void deleteAll(){
            repository.deleteAll();
        }

        public LiveData<List<ExerciseToDo>> getAll(){
            return list;
        }
}
