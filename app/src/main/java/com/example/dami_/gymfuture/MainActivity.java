package com.example.dami_.gymfuture;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.dami_.gymfuture.Database.DatabaseApp;
import com.example.dami_.gymfuture.Model.Exercise;
import com.example.dami_.gymfuture.app.app;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class MainActivity extends AppCompatActivity
{
    public static DatabaseApp databaseApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseApp = app.getDatabaseBuilder(this);


        new Thread(new Runnable()
        {
            @Override
            public void run() {
                try
                {
                    List<Exercise> listExercises = databaseApp.daoExercise().getExercises();

                    for (Exercise exercise : listExercises) {
                        System.out.println("DB_NAME: " + exercise.getName());
                        System.out.println("DB_DESCRIPTION: " + exercise.getDescription());
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
