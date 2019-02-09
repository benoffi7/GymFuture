package com.example.dami_.gymfuture;

import android.content.Intent;
import android.graphics.Color;
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
import com.example.dami_.gymfuture.app.app;

import java.util.List;

public class SelectObjetiveActivity extends AppCompatActivity {
    public static DatabaseApp databaseApp;
    //public RoutinesAdapter adapter;
    private RadioGroup radioGroup;
    public List<Objetive> listObjetives;
    public List<Routine> listRoutines;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_objetive);
        databaseApp = app.getDatabaseBuilder(this);
        this.radioGroup = findViewById(R.id.main_radio_button);
        Button btn_confirm = findViewById(R.id.btn_confirm_objetive);

        new Thread(new Runnable()
        {
            @Override
            public void run() {
                try
                {
                    SelectObjetiveActivity.this.listObjetives = databaseApp.objetiveDao().getAll();
                    SelectObjetiveActivity.this.listRoutines = databaseApp.routineDao().getAll();
                    setRadioButtons();
                    //MainActivity.this.setRoutinesAdapter();
                }
                catch (Exception e)
                {
                    Toast.makeText(getApplicationContext(), "ASD", Toast.LENGTH_SHORT).show();
                }
            }
        }).start();

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

    public void updateObjetivesRoutines(RadioButton rb){
        final String nameSelectedObjetive = (String) rb.getText();
            new Thread(new Runnable() {
                @Override
                public void run() {

                    Objetive objetive = databaseApp.objetiveDao().getByName(nameSelectedObjetive);
                    for(Routine routine : listRoutines){
                        routine.setId_objetive(objetive.getKey());
                        databaseApp.routineDao().update(routine);
                        System.out.println("OBJETIVO: " + databaseApp.objetiveDao().getById(routine.getId_objetive()).getName());

                    }

                    Intent intent = new Intent(SelectObjetiveActivity.this,MainActivity.class );
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);


                }
            }).start();
    }

    public void setRadioButtons(){
        int count = 0;
        for(Objetive obj : listObjetives){
            RadioButton rb = new RadioButton(this);
            rb.setText(obj.getName());
            rb.setTextSize(30);
            rb.setTextColor(Color.rgb(25,65,126));
            rb.setId(count++);
            this.radioGroup.addView(rb);
        }
    }
}
