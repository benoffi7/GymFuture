package com.example.dami_.gymfuture;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.dami_.gymfuture.Database.DatabaseApp;
import com.example.dami_.gymfuture.Model.Category;
import com.example.dami_.gymfuture.Model.Day;
import com.example.dami_.gymfuture.Model.Exercise;
import com.example.dami_.gymfuture.Model.ExerciseToDo;
import com.example.dami_.gymfuture.Model.Objetive;
import com.example.dami_.gymfuture.Model.Routine;
import com.example.dami_.gymfuture.ViewModel.CategoryViewModel;
import com.example.dami_.gymfuture.ViewModel.DayViewModel;
import com.example.dami_.gymfuture.ViewModel.ExerciseToDoViewModel;
import com.example.dami_.gymfuture.ViewModel.ExerciseViewModel;
import com.example.dami_.gymfuture.ViewModel.ObjetiveViewModel;
import com.example.dami_.gymfuture.ViewModel.RoutineViewModel;
import com.example.dami_.gymfuture.app.app;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SplashActivity extends AppCompatActivity
{
    public static DatabaseApp databaseApp;
    private final FirebaseDatabase database = FirebaseDatabase.getInstance();

    private ObjetiveViewModel objetiveViewModel;
    private ExerciseViewModel exerciseViewModel;
    private RoutineViewModel routineViewModel;
    private DayViewModel dayViewModel;
    private ExerciseToDoViewModel exerciseToDoViewModel;
    private CategoryViewModel categoryViewModel;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        database.setPersistenceEnabled(true);
        databaseApp = app.getDatabaseBuilder(this);

        objetiveViewModel = ViewModelProviders.of(this).get(ObjetiveViewModel.class);
        exerciseViewModel = ViewModelProviders.of(this).get(ExerciseViewModel.class);
        routineViewModel = ViewModelProviders.of(this).get(RoutineViewModel.class);
        dayViewModel = ViewModelProviders.of(this).get(DayViewModel.class);
        exerciseToDoViewModel = ViewModelProviders.of(this).get(ExerciseToDoViewModel.class);
        categoryViewModel = ViewModelProviders.of(this).get(CategoryViewModel.class);

        load();

        Intent intent = new Intent(this,SelectObjetiveActivity.class );
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    public void load(){
        loadCategories();
    }

    private void loadCategories(){
        DatabaseReference myRef = database.getReference().child("category");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {
                categoryViewModel.deleteAll();

                for (DataSnapshot node : dataSnapshot.getChildren())
                {
                    String key = node.getKey();
                    String name = (String) node.child("name").getValue();
                    String url_image = (String) node.child("url_image").getValue();
                    assert key != null;
                    final Category category = new Category(key, name ,url_image);
                    categoryViewModel.insert(category);
                }
                loadExercises();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    public void loadExercises(){
        //OBTENGO REFERENCIA
        DatabaseReference myRef = database.getReference().child("exercises");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {
                exerciseViewModel.deleteAll();

                for (DataSnapshot node : dataSnapshot.getChildren())
                {
                    String key = node.getKey();
                    String name = (String) node.child("name").getValue();
                    String description = (String) node.child("description").getValue();
                    String url_image = (String) node.child("url_image").getValue();
                    assert key != null;
                    final Exercise exercise = new Exercise(key, name, description,url_image);
                    exerciseViewModel.insert(exercise);
                }
                loadObjetives();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void loadObjetives(){
        //OBTENGO REFERENCIA
        DatabaseReference myRef = database.getReference().child("objetives");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {
                objetiveViewModel.deleteAll();

                for (DataSnapshot node : dataSnapshot.getChildren())
                {
                    String key = node.getKey();
                    String name = (String) node.child("name").getValue();
                    String repetitionsString = (String) node.child("repetitions").getValue();
                    assert repetitionsString != null;
                    Byte repetitions = Byte.parseByte(repetitionsString);
                    String seriesString = (String) node.child("series").getValue();
                    assert seriesString != null;
                    Byte series = Byte.parseByte(seriesString);
                    String breakTime = (String) node.child("breakTime").getValue();

                    assert key != null;
                    final Objetive objetive = new Objetive(key,breakTime,name,repetitions,series);

                    objetiveViewModel.insert(objetive);
                }
                loadRoutines();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void loadRoutines(){
        //OBTENGO REFERENCIA
        DatabaseReference myRef = database.getReference().child("routines");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {
                routineViewModel.deleteAll();

                for (DataSnapshot node : dataSnapshot.getChildren())
                {
                    String key = node.getKey();
                    String name = (String) node.child("name").getValue();
                    String id_category = (String) node.child("id_category").getValue();
                    String url_image = (String) node.child("url_image").getValue();
                    String id_objetive = (String) node.child("id_objetive").getValue();
                    assert key != null;
                    final Routine routine = new Routine(key,name,id_category,url_image,id_objetive);
                    routineViewModel.insert(routine);
                }
                loadDays();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void loadDays(){
        //OBTENGO REFERENCIA
        DatabaseReference myRef = database.getReference().child("days");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {
               dayViewModel.deleteAll();
                for (DataSnapshot node : dataSnapshot.getChildren())
                {
                    String key = node.getKey();
                    long numberLong = (long) node.child("number").getValue();
                    String numberString = String.valueOf(numberLong);
                    Byte number = Byte.parseByte(numberString);
                    String id_routine = (String) node.child("id_routine").getValue();
                    assert key != null;
                    final Day day = new Day(key,number,id_routine);
                    dayViewModel.insert(day);
                }
                loadExercisesToDo();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void loadExercisesToDo(){
        //OBTENGO REFERENCIA
        DatabaseReference myRef = database.getReference().child("exerciseToDo");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {
                exerciseToDoViewModel.deleteAll();
                for (DataSnapshot node : dataSnapshot.getChildren())
                {
                    String key = node.getKey();
                    String id_day = (String) node.child("id_day").getValue();
                    String id_exercise = (String) node.child("id_exercise").getValue();
                    boolean hasKilograms = (boolean) node.child("hasKilograms").getValue();
                    String time = (String) node.child("time").getValue();
                    assert key != null;

                    ExerciseToDo exerciseToDo = new ExerciseToDo(key,id_day,
                            id_exercise,hasKilograms,time);

                    exerciseToDoViewModel.insert(exerciseToDo);
                }


            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
