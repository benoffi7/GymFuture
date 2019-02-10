package com.example.dami_.gymfuture;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.example.dami_.gymfuture.Database.DatabaseApp;
import com.example.dami_.gymfuture.Model.Day;
import com.example.dami_.gymfuture.Model.Exercise;
import com.example.dami_.gymfuture.Model.ExerciseToDo;
import com.example.dami_.gymfuture.Model.Objetive;
import com.example.dami_.gymfuture.Model.Routine;
import com.example.dami_.gymfuture.app.app;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class SplashActivity extends AppCompatActivity
{
    public static DatabaseApp databaseApp;
    private final FirebaseDatabase database = FirebaseDatabase.getInstance();

    int contador = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        database.setPersistenceEnabled(true);

        databaseApp = app.getDatabaseBuilder(this);
        load();
    }

    public void load(){
        loadExercises();
    }

    public void loadExercises(){
        //OBTENGO REFERENCIA
        DatabaseReference myRef = database.getReference().child("exercises");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        databaseApp.exerciseDao().truncateTable();
                        final int cantidad =  (int) dataSnapshot.getChildrenCount();
                        for (DataSnapshot node : dataSnapshot.getChildren())
                        {
                            String key = node.getKey();
                            String name = (String) node.child("name").getValue();
                            String description = (String) node.child("description").getValue();
                            String url_image = (String) node.child("url_image").getValue();
                            assert key != null;
                            final Exercise exercise = new Exercise(key, name, description,url_image);
                            exercise.setIdExercise(key);
                            new Thread(new Runnable()
                            {
                                @Override
                                public void run() {

                                        databaseApp.exerciseDao().addExercise(exercise);
                                        contador++;
                                        if (contador == cantidad)
                                        {
                                            contador=0;
                                            loadObjetives();
                                        }

                                }
                            }).start();
                        }
                    }
                }).start();
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
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        databaseApp.objetiveDao().truncateTable();
                        final int cantidad =  (int) dataSnapshot.getChildrenCount();
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

                            new Thread(new Runnable()
                            {
                                @Override
                                public void run() {
                                        databaseApp.objetiveDao().add(objetive);
                                        contador++;
                                        if (contador == cantidad)
                                        {
                                            contador=0;
                                            loadRoutines();
                                        }
                                }
                            }).start();
                        }
                    }
                }).start();
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
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        databaseApp.routineDao().truncateTable();
                        final int cantidad =  (int) dataSnapshot.getChildrenCount();
                        for (DataSnapshot node : dataSnapshot.getChildren())
                        {
                            String key = node.getKey();
                            String name = (String) node.child("name").getValue();
                            String id_objetive = (String) node.child("id_objetive").getValue();
                            assert key != null;
                            final Routine routine = new Routine(key, name,id_objetive);
                            routine.setKey(key);
                            new Thread(new Runnable()
                            {
                                @Override
                                public void run() {
                                        databaseApp.routineDao().addRoutine(routine);
                                        contador++;
                                        if (contador == cantidad)
                                        {
                                            contador=0;
                                            loadDays();
                                        }
                                }
                            }).start();
                        }
                    }
                }).start();
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
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        databaseApp.dayDao().truncateTable();
                        final int cantidad =  (int) dataSnapshot.getChildrenCount();
                        for (DataSnapshot node : dataSnapshot.getChildren())
                        {
                            String key = node.getKey();
                            long numberLong = (long) node.child("number").getValue();
                            String numberString = String.valueOf(numberLong);
                            Byte number = Byte.parseByte(numberString);
                            String id_routine = (String) node.child("id_routine").getValue();
                            assert key != null;
                            final Day day = new Day(key,number,id_routine);

                            new Thread(new Runnable()
                            {
                                @Override
                                public void run() {
                                        databaseApp.dayDao().add(day);
                                        contador++;
                                        if (contador == cantidad)
                                        {
                                            contador=0;
                                            loadExercisesToDo();
                                        }
                                }
                            }).start();
                        }
                    }
                }).start();
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
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        databaseApp.exerciseToDoDao().truncateTable();
                        final int cantidad =  (int) dataSnapshot.getChildrenCount();
                        for (DataSnapshot node : dataSnapshot.getChildren())
                        {
                            String key = node.getKey();
                            String id_day = (String) node.child("id_day").getValue();
                            String id_exercise = (String) node.child("id_exercise").getValue();
                            boolean hasKilograms = (boolean) node.child("hasKilograms").getValue();
                            String time = (String) node.child("time").getValue();
                            assert key != null;

                            final ExerciseToDo exerciseToDo = new ExerciseToDo(key,id_day,
                                    id_exercise,hasKilograms,time);

                            new Thread(new Runnable()
                            {
                                @Override
                                public void run() {
                                        databaseApp.exerciseToDoDao().add(exerciseToDo);
                                        contador++;
                                        if (contador == cantidad)
                                        {
                                            contador=0;

                                            //HACER UN IF PARA VERIFICAR SI EL USUARIO YA TIENE UN OBJETIVO O NO

                                            //IF NO_EXISTE_OBJETIVO

                                                Intent intent = new Intent(SplashActivity.this,SelectObjetiveActivity.class );
                                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                startActivity(intent);

                                             //ELSE
                                                //INTENT intent = new Intent() --> mandar la MainActivity
                                        }
                                }
                            }).start();
                        }
                    }
                }).start();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
