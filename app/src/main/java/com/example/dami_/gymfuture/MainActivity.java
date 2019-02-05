package com.example.dami_.gymfuture;

import android.arch.persistence.room.Room;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.dami_.gymfuture.Database.DatabaseApp;
import com.example.dami_.gymfuture.Model.Exercise;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static DatabaseApp databaseApp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //INICIO ROOM DATABASE
        databaseApp = Room.databaseBuilder(getApplicationContext(),
                DatabaseApp.class, "gym_future_db").fallbackToDestructiveMigration().build();

        //INICIO FIREBASE
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        database.setPersistenceEnabled(true);

        //OBTENGO REFERENCIA
        DatabaseReference myRef = database.getReference().child("exercises");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        databaseApp.daoExercise().truncateTable();
                    }
                }).start();
                for (DataSnapshot node : dataSnapshot.getChildren()) {
                    String key = node.getKey();
                    String name = (String) node.child("name").getValue();
                    String description = (String) node.child("description").getValue();
                    final Exercise exercise = new Exercise(name, "undefined", description);
                    exercise.setIdExercise(key);
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                databaseApp.daoExercise().addExercise(exercise);
                            } catch (Exception e) {
                                Toast.makeText(getApplicationContext(), "ASD", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }).start();
                }

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        List<Exercise> listExercises = databaseApp.daoExercise().getExercises();

                        for (Exercise exercise : listExercises) {
                            System.out.println("DB_NAME: " + exercise.getName());
                            System.out.println("DB_DESCRIPTION: " + exercise.getDescription());
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
