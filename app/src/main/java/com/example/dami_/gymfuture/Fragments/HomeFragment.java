package com.example.dami_.gymfuture.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.dami_.gymfuture.Activities.RoutinesActivity;
import com.example.dami_.gymfuture.Model.Exercise;
import com.example.dami_.gymfuture.R;
import com.example.dami_.gymfuture.ViewModel.ExerciseViewModel;
import com.example.dami_.gymfuture.adapters.ExercisesAdapter;


public class HomeFragment extends Fragment implements ExercisesAdapter.ItemClickListener {

    private ExerciseViewModel exerciseListViewModel;

    private ExercisesAdapter adapter;
    private Button btnRoutines;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home,container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnRoutines = getView().findViewById(R.id.btnRoutines);
        btnRoutines.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRoutinesActivity();
            }
        });
    }

    public void openRoutinesActivity(){
        Intent intent = new Intent(getActivity() , RoutinesActivity.class);
        startActivity(intent);
    }

    @Override
    public void onItemClick(View view, int position) {
        Exercise exercise = adapter.getItem(position);
        exerciseListViewModel.delete(exercise);
    }
}
