package com.example.dami_.gymfuture;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.example.dami_.gymfuture.Database.DatabaseApp;
import com.example.dami_.gymfuture.Model.Exercise;
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
    int contador = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);



        //INICIO ROOM DATABASE
        databaseApp = app.getDatabaseBuilder(this);

        //INICIO FIREBASE
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        database.setPersistenceEnabled(true);

        //OBTENGO REFERENCIA
        DatabaseReference myRef = database.getReference().child("exercises");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        databaseApp.daoExercise().truncateTable();
                        final int cantidad =  (int) dataSnapshot.getChildrenCount();
                        Log.d("gymfuture","cantidad: "+cantidad);
                        for (DataSnapshot node : dataSnapshot.getChildren())
                        {
                            String key = node.getKey();
                            String name = (String) node.child("name").getValue();
                            String description = (String) node.child("description").getValue();
                            final Exercise exercise = new Exercise(key, name, description);
                            exercise.setIdExercise(key);
                            new Thread(new Runnable()
                            {
                                @Override
                                public void run() {
                                    try
                                    {
                                        databaseApp.daoExercise().addExercise(exercise);
                                        contador++;
                                        if (contador == cantidad)
                                        {
                                            Intent intent = new Intent(SplashActivity.this,MainActivity.class );
                                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                            startActivity(intent);
                                        }
                                    }
                                    catch (Exception e)
                                    {
                                        Toast.makeText(getApplicationContext(), "ASD", Toast.LENGTH_SHORT).show();
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
