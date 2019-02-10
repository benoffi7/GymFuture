package com.example.dami_.gymfuture.Fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.dami_.gymfuture.Database.DatabaseApp;
import com.example.dami_.gymfuture.MainActivity;
import com.example.dami_.gymfuture.Model.Exercise;
import com.example.dami_.gymfuture.R;
import com.example.dami_.gymfuture.ViewModel.ExerciseListViewModel;
import com.example.dami_.gymfuture.adapters.ExercisesAdapter;
import com.example.dami_.gymfuture.app.app;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements ExercisesAdapter.ItemClickListener {

    private DatabaseApp databaseApp;
    private ExerciseListViewModel exerciseListViewModel;

    private ExercisesAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home,container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        setExerciseAdapter(view);
        exerciseListViewModel = ViewModelProviders.of(this).get(ExerciseListViewModel.class);
        exerciseListViewModel.getListExercises().observe(HomeFragment.this, new Observer<List<Exercise>>() {
            @Override
            public void onChanged(@Nullable List<Exercise> exercises) {
                adapter.addItems(exercises);
            }
        });
        super.onViewCreated(view, savedInstanceState);
    }

    public void setExerciseAdapter(View view){
        // set up the RecyclerView
       RecyclerView recyclerView = view.findViewById(R.id.rv_exercise);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new ExercisesAdapter(getContext(), new ArrayList<Exercise>());
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
    }



    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(getContext(), "You clicked " + adapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();
    }
}
