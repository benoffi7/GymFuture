package com.example.dami_.gymfuture.Fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dami_.gymfuture.Database.DatabaseApp;
import com.example.dami_.gymfuture.Model.Exercise;
import com.example.dami_.gymfuture.R;
import com.example.dami_.gymfuture.ViewModel.ExerciseViewModel;
import com.example.dami_.gymfuture.adapters.ExercisesAdapter;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements ExercisesAdapter.ItemClickListener {

    private ExerciseViewModel exerciseListViewModel;

    private ExercisesAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home,container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setExerciseAdapter(view);
        exerciseListViewModel = ViewModelProviders.of(getActivity()).get(ExerciseViewModel.class);

        exerciseListViewModel.getAll().observe(HomeFragment.this, new Observer<List<Exercise>>() {
            @Override
            public void onChanged(@Nullable List<Exercise> exercises) {
                adapter.addItems(exercises);
            }
        });
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
        Exercise exercise = adapter.getItem(position);
        exerciseListViewModel.delete(exercise);
    }
}
