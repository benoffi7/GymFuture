package com.example.dami_.gymfuture.Activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.dami_.gymfuture.Model.Routine;
import com.example.dami_.gymfuture.R;
import com.example.dami_.gymfuture.ViewModel.RoutineViewModel;
import com.example.dami_.gymfuture.adapters.RoutinesAdapter;

import java.util.ArrayList;
import java.util.List;

public class RoutineActivity extends AppCompatActivity implements RoutinesAdapter.ItemClickListener{

    private RoutinesAdapter routinesAdapter;
    private RoutineViewModel routineViewModel;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routine);

        String categoryName = getIntent().getStringExtra("CATEGORY_NAME");
        String categoryId = getIntent().getStringExtra("CATEGORY_ID");

        setTitle(categoryName);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final ProgressBar PROGRESS_BAR = findViewById(R.id.progress_loading);
        PROGRESS_BAR.setIndeterminate(true);

        recyclerView = findViewById(R.id.rv_routines);
        recyclerView.setVisibility(View.GONE);

        routineViewModel = ViewModelProviders.of(this).get(RoutineViewModel.class);

        routineViewModel.getByCategory(categoryId).observe(this, new Observer<List<Routine>>() {
            @Override
            public void onChanged(@Nullable List<Routine> routines) {
                loadRoutinesAdapter();
                routinesAdapter.addItems(routines);

            }
        });
        PROGRESS_BAR.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);


    }

    public void loadRoutinesAdapter(){
        // set up the RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        routinesAdapter = new RoutinesAdapter(this, new ArrayList<Routine>());
        routinesAdapter.setClickListener(this);
        recyclerView.setAdapter(routinesAdapter);
    }

    @Override
    public void onItemClick(View view, int position) {
        Routine routine = routinesAdapter.getItem(position);
        Toast.makeText(this, routine.getName() , Toast.LENGTH_SHORT).show();
    }
}
