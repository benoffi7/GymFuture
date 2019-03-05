package com.example.dami_.gymfuture;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.dami_.gymfuture.Model.Category;
import com.example.dami_.gymfuture.Model.Day;
import com.example.dami_.gymfuture.Model.Exercise;
import com.example.dami_.gymfuture.Model.ExerciseToDo;
import com.example.dami_.gymfuture.Model.Objetive;
import com.example.dami_.gymfuture.Model.Routine;
import com.example.dami_.gymfuture.Model.UserObjetive;
import com.example.dami_.gymfuture.ViewModel.CategoryViewModel;
import com.example.dami_.gymfuture.ViewModel.DayViewModel;
import com.example.dami_.gymfuture.ViewModel.ExerciseToDoViewModel;
import com.example.dami_.gymfuture.ViewModel.ExerciseViewModel;
import com.example.dami_.gymfuture.ViewModel.ObjetiveViewModel;
import com.example.dami_.gymfuture.ViewModel.RoutineViewModel;
import com.example.dami_.gymfuture.ViewModel.UserObjetiveViewModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SplashActivity extends AppCompatActivity
{
    private final FirebaseFirestore DB_FIRESTORE = FirebaseFirestore.getInstance();

    private UserObjetive userObjetive;
    private ObjetiveViewModel objetiveViewModel;
    private ExerciseViewModel exerciseViewModel;
    private RoutineViewModel routineViewModel;
    private DayViewModel dayViewModel;
    private ExerciseToDoViewModel exerciseToDoViewModel;
    private CategoryViewModel categoryViewModel;
    private UserObjetiveViewModel userObjetiveViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        objetiveViewModel = ViewModelProviders.of(this).get(ObjetiveViewModel.class);
        exerciseViewModel = ViewModelProviders.of(this).get(ExerciseViewModel.class);
        routineViewModel = ViewModelProviders.of(this).get(RoutineViewModel.class);
        dayViewModel = ViewModelProviders.of(this).get(DayViewModel.class);
        exerciseToDoViewModel = ViewModelProviders.of(this).get(ExerciseToDoViewModel.class);
        categoryViewModel = ViewModelProviders.of(this).get(CategoryViewModel.class);
        userObjetiveViewModel = ViewModelProviders.of(this).get(UserObjetiveViewModel.class);

        userObjetiveViewModel.get().observe(this, new Observer<UserObjetive>() {
            @Override
            public void onChanged(@Nullable UserObjetive uo) {
                userObjetive = uo;

            }
        });

        load();

    }

    public void startMainActivity(){
        Intent intent = new Intent(this,MainActivity.class );
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    public void startSelectObjetiveActiviry(){
        Intent intent = new Intent(this,SelectObjetiveActivity.class );
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);

    }
    public boolean hasUserObjetive(){
        boolean flag = false;
        if(userObjetive != null){
            if(userObjetive.getId_objetive() != null){
                flag = true;
            }
        }else{

            userObjetiveViewModel.insert(new UserObjetive());
        }
        return flag;

    }
    public void load(){
        loadCategories();
    }

    private void loadCategories(){
        CollectionReference db_ref_categories = DB_FIRESTORE.collection("category");
        db_ref_categories.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    categoryViewModel.deleteAll();
                    for(QueryDocumentSnapshot document : Objects.requireNonNull(task.getResult())){
                        String id = document.getId();
                        String name = (String) document.getData().get("name");
                        String url_image = (String) document.getData().get("url_image");
                        Category category = new Category(id,name,url_image);
                        categoryViewModel.insert(category);
                    }
                }
                loadExercises();
            }
        });
    }
    public void loadExercises(){
        CollectionReference db_ref_exercises = DB_FIRESTORE.collection("exercises");
        db_ref_exercises.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    exerciseViewModel.deleteAll();
                    for(QueryDocumentSnapshot document : Objects.requireNonNull(task.getResult())){
                        String id = document.getId();
                        String name = (String) document.getData().get("name");
                        String description = (String) document.getData().get("description");
                        String url_image = (String) document.getData().get("url_image");
                        Exercise exercise = new Exercise(id,name,description,url_image);
                        exerciseViewModel.insert(exercise);
                    }
                }
                loadObjetives();
            }
        });
    }

    public void loadObjetives(){
        CollectionReference db_ref_objetives = DB_FIRESTORE.collection("objetives");
        db_ref_objetives.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){

                    String idUserObjetive = null;
                    if(userObjetive != null){
                        if(userObjetive.getId_objetive() != null)
                            idUserObjetive = userObjetive.getId_objetive();
                    }

                    objetiveViewModel.deleteAll();
                    for(QueryDocumentSnapshot document : Objects.requireNonNull(task.getResult())){
                        String id = document.getId();
                        String name = (String) document.getData().get("name");
                        String series = (String) document.getData().get("series");
                        assert series != null;
                        String repetitions = (String) document.getData().get("repetitions");
                        assert repetitions != null;
                        String breakTime = (String) document.getData().get("breakTime");
                        String url_image = (String) document.getData().get("url_image");

                        Objetive objetive = new Objetive(id,breakTime,name,
                                Byte.parseByte(repetitions),Byte.parseByte(series), url_image);
                        objetiveViewModel.insert(objetive);

                        if(idUserObjetive != null){
                            if(objetive.getKey().equals(idUserObjetive)){
                                userObjetive.setId_objetive(objetive.getKey());
                                userObjetiveViewModel.update(userObjetive);
                            }
                        }

                    }
                }
                loadRoutines();
            }
        });
    }

    public void loadRoutines(){
        CollectionReference db_ref_routines = DB_FIRESTORE.collection("routines");
        db_ref_routines.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    routineViewModel.deleteAll();
                    for(QueryDocumentSnapshot document : Objects.requireNonNull(task.getResult())){
                        String id = document.getId();
                        String name = (String) document.getData().get("name");
                        String id_category = (String) document.getData().get("id_category");
                        String url_image = (String) document.getData().get("url_image");
                        Routine routine = new Routine(id,name,id_category,url_image);
                        routineViewModel.insert(routine);
                    }
                }
                loadDays();
            }
        });
    }

    public void loadDays(){
        CollectionReference db_ref_days = DB_FIRESTORE.collection("days");
        db_ref_days.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    dayViewModel.deleteAll();
                    for(QueryDocumentSnapshot document : Objects.requireNonNull(task.getResult())){
                        String id = document.getId();
                        long numberLong = (long) document.getData().get("number");
                        String numberString = String.valueOf(numberLong);
                        Byte number = Byte.parseByte(numberString);
                        String id_routine = (String) document.getData().get("id_routine");
                        Day day = new Day(id,number,id_routine);
                        dayViewModel.insert(day);
                    }
                }
                loadExercisesToDo();
            }
        });
    }

    public void loadExercisesToDo(){
        CollectionReference db_ref_exercisesToDo = DB_FIRESTORE.collection("exerciseToDo");
        db_ref_exercisesToDo.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    exerciseToDoViewModel.deleteAll();
                    for(QueryDocumentSnapshot document : Objects.requireNonNull(task.getResult())){
                        String id = document.getId();
                        String id_day = (String) document.getData().get("id_day");
                        String id_exercise = (String) document.getData().get("id_exercise");
                        boolean hasKilograms = (boolean) document.getData().get("hasKilograms");
                        String time = (String) document.getData().get("time");
                        ExerciseToDo exerciseToDo = new ExerciseToDo(id,id_day,id_exercise,hasKilograms,time);
                        exerciseToDoViewModel.insert(exerciseToDo);
                    }

                    if(!hasUserObjetive()) {
                        startSelectObjetiveActiviry();
                    }
                    else{
                        startMainActivity();
                    }
                }
            }
        });
    }

}
