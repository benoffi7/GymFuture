package com.example.dami_.gymfuture;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.dami_.gymfuture.Database.DatabaseApp;
import com.example.dami_.gymfuture.Model.Objetive;
import com.example.dami_.gymfuture.Model.Routine;
import com.example.dami_.gymfuture.ViewModel.ObjetiveViewModel;
import com.example.dami_.gymfuture.ViewModel.RoutineViewModel;
import com.example.dami_.gymfuture.app.app;

import java.util.List;

public class SelectObjetiveActivity extends AppCompatActivity {
    //public RoutinesAdapter adapter;
    private RadioGroup radioGroup;

    private ObjetiveViewModel objetiveViewModel;
    private RoutineViewModel routineViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_objetive);
        this.radioGroup = findViewById(R.id.main_radio_button);
        final Button btn_confirm = findViewById(R.id.btn_confirm_objetive);

        objetiveViewModel = ViewModelProviders.of(this).get(ObjetiveViewModel.class);
        routineViewModel = ViewModelProviders.of(this).get(RoutineViewModel.class);

        objetiveViewModel.getAll().observe(this, new Observer<List<Objetive>>() {
            @Override
            public void onChanged(@Nullable List<Objetive> objetives) {
                setRadioButtons(objetives);
            }
        });
        routineViewModel.getAll().observe(this, new Observer<List<Routine>>() {
            @Override
            public void onChanged(@Nullable List<Routine> routines) {
                btn_confirm.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        try {
                            int id_button = radioGroup.getCheckedRadioButtonId();
                            RadioButton rb = findViewById(id_button);
                            updateObjetivesRoutines(rb);
                        }catch (NullPointerException e){
                            Toast.makeText(SelectObjetiveActivity.this , "ELIGE UNA OPCION" , Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });





    }

    public void updateObjetivesRoutines(RadioButton rb){
        final String nameSelectedObjetive = (String) rb.getText();
        Objetive objetive = objetiveViewModel.getByName(nameSelectedObjetive);
        if(objetive != null){
            if(routineViewModel.getAll().getValue() != null) {
                for (Routine routine : routineViewModel.getAll().getValue()) {
                    routine.setId_objetive(objetive.getKey());
                    routineViewModel.update(routine);
                }
                Intent intent = new Intent(SelectObjetiveActivity.this,MainActivity.class );
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        }else{
            Toast.makeText(this , "ERROR! NO SE ENCONTRO EL OBJETIVO" , Toast.LENGTH_LONG)
            .show();
        }
    }

    public void setRadioButtons(List<Objetive> objetives){
        radioGroup.removeAllViews();
        int count = 0;
        radioGroup.clearCheck();
        for(Objetive obj : objetives){
            RadioButton rb = new RadioButton(this);
            rb.setText(obj.getName());
            rb.setTextSize(30);
            rb.setTextColor(Color.rgb(25,65,126));
            rb.setId(count++);
            this.radioGroup.addView(rb);
        }
    }
}
