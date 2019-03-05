package com.example.dami_.gymfuture.Activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.dami_.gymfuture.Model.Category;
import com.example.dami_.gymfuture.Model.Exercise;
import com.example.dami_.gymfuture.Model.Routine;
import com.example.dami_.gymfuture.R;
import com.example.dami_.gymfuture.ViewModel.CategoryViewModel;
import com.example.dami_.gymfuture.adapters.CategoriesAdapter;
import com.example.dami_.gymfuture.adapters.RoutinesAdapter;

import java.util.ArrayList;
import java.util.List;

import static java.security.AccessController.getContext;

public class CategoriesActivity extends AppCompatActivity implements CategoriesAdapter.ItemClickListener{

    private CategoryViewModel categoryViewModel;
    private CategoriesAdapter categoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        setTitle(R.string.title_category);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        categoryViewModel = ViewModelProviders.of(this).get(CategoryViewModel.class);

        categoryViewModel.getAll().observe(this, new Observer<List<Category>>() {
            @Override
            public void onChanged(@Nullable List<Category> categories) {
                loadCategoriesAdapter();
                categoryAdapter.addItems(categories);

            }
        });

    }

    public void loadCategoriesAdapter(){
        // set up the RecyclerView
         RecyclerView recyclerView = findViewById(R.id.rv_categories);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        categoryAdapter = new CategoriesAdapter(this, new ArrayList<Category>());
        categoryAdapter.setClickListener(this);
        recyclerView.setAdapter(categoryAdapter);
    }

    @Override
    public void onItemClick(View view, int position) {
        Category category = categoryAdapter.getItem(position);
        Intent intent = new Intent(this, RoutineActivity.class);
        intent.putExtra("CATEGORY_ID", category.getKey());
        intent.putExtra("CATEGORY_NAME", category.getName());
        startActivity(intent);
    }
}
