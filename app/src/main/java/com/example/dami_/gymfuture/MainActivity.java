package com.example.dami_.gymfuture;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.dami_.gymfuture.Database.DatabaseApp;
import com.example.dami_.gymfuture.Model.Exercise;
import com.example.dami_.gymfuture.Model.Objetive;
import com.example.dami_.gymfuture.Model.Routine;
import com.example.dami_.gymfuture.adapters.RoutinesAdapter;
import com.example.dami_.gymfuture.app.app;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class MainActivity extends AppCompatActivity // implements RoutinesAdapter.ItemClickListener
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }




    public void setRoutinesAdapter(){
    /*    // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.rv_routines);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RoutinesAdapter(this, listRoutines);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
        */

    }

    /*
    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(this, "You clicked " + adapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();
    }
    */

}
