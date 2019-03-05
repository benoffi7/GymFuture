package com.example.dami_.gymfuture;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.dami_.gymfuture.Database.DatabaseApp;
import com.example.dami_.gymfuture.Model.Objetive;
import com.example.dami_.gymfuture.Model.Routine;
import com.example.dami_.gymfuture.Model.UserObjetive;
import com.example.dami_.gymfuture.ViewModel.ObjetiveViewModel;
import com.example.dami_.gymfuture.ViewModel.RoutineViewModel;
import com.example.dami_.gymfuture.ViewModel.UserObjetiveViewModel;
import com.example.dami_.gymfuture.adapters.ObjetivesAdapter;
import com.example.dami_.gymfuture.app.app;

import java.util.ArrayList;
import java.util.List;

public class SelectObjetiveActivity extends AppCompatActivity implements  ObjetivesAdapter.ItemClickListener {
    //public RoutinesAdapter adapter;

    private UserObjetive userObjetive;
    private ObjetivesAdapter adapter;
    private ObjetiveViewModel objetiveViewModel;
    private UserObjetiveViewModel userObjetiveViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(savedInstanceState == null) {
            setContentView(R.layout.activity_select_objetive);


            //LOAD VIEWMODELS
            objetiveViewModel = ViewModelProviders.of(this).get(ObjetiveViewModel.class);
            userObjetiveViewModel = ViewModelProviders.of(this).get(UserObjetiveViewModel.class);

            loadObjetivesAdapter();

            userObjetiveViewModel.get().observe(this, new Observer<UserObjetive>() {
                @Override
                public void onChanged(@Nullable UserObjetive uo) {
                    userObjetive = uo;
                }
            });
            objetiveViewModel.getAll().observe(this, new Observer<List<Objetive>>() {
                @Override
                public void onChanged(@Nullable List<Objetive> objetives) {
                    adapter.addItems(objetives);

                }
            });


        }
    }


    public void loadObjetivesAdapter(){
        RecyclerView recyclerView = findViewById(R.id.rv_objetives);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ObjetivesAdapter(this, new ArrayList<Objetive>());
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
    }
    public void startMainActivity(){
        Intent intent = new Intent(this,MainActivity.class );
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public void onItemClick(View view, int position) {
        final Objetive o = adapter.getItem(position);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setInverseBackgroundForced(true);
        builder.setTitle(o.getName());
        builder.setMessage("¿Seguro que quieres elegir este objetivo? ");
        builder.setPositiveButton("Si, confirmar",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(userObjetive != null){
                            userObjetive.setId_objetive(o.getKey());
                            userObjetiveViewModel.update(userObjetive);
                            startMainActivity();
                        } else Toast.makeText(SelectObjetiveActivity.this,
                                "NO SE ENCONTRO EL USUARIO" , Toast.LENGTH_LONG).show();
                    }
                });
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
