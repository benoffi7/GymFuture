package com.example.dami_.gymfuture.Fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.dami_.gymfuture.Activities.CategoriesActivity;
import com.example.dami_.gymfuture.Model.Exercise;
import com.example.dami_.gymfuture.Model.Objetive;
import com.example.dami_.gymfuture.Model.UserObjetive;
import com.example.dami_.gymfuture.R;
import com.example.dami_.gymfuture.ViewModel.ExerciseViewModel;
import com.example.dami_.gymfuture.ViewModel.ObjetiveViewModel;
import com.example.dami_.gymfuture.ViewModel.UserObjetiveViewModel;
import com.example.dami_.gymfuture.adapters.ExercisesAdapter;

import java.util.List;


public class HomeFragment extends Fragment  {

    private Button btnRoutines;
    private UserObjetive userObjetive;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home,container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        UserObjetiveViewModel userObjetiveViewModel =
                ViewModelProviders.of(getActivity()).get(UserObjetiveViewModel.class);

        final ObjetiveViewModel objetiveViewModel = ViewModelProviders.of(getActivity()).get(ObjetiveViewModel.class);

        userObjetiveViewModel.get().observe(this, new Observer<UserObjetive>() {
            @Override
            public void onChanged(@Nullable UserObjetive uo) {
                userObjetive = uo;
                Objetive o = objetiveViewModel.getById(userObjetive.getId_objetive());
                Toast.makeText(getContext(),"TU OBJETIVO: " + o.getName() , Toast.LENGTH_LONG).show();

            }
        });


        btnRoutines = getView().findViewById(R.id.btnRoutines);
        btnRoutines.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRoutinesActivity();
            }
        });
    }

    public void openRoutinesActivity(){
        Intent intent = new Intent(getActivity() , CategoriesActivity.class);
        startActivity(intent);
    }
}
