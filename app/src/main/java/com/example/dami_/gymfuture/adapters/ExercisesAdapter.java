package com.example.dami_.gymfuture.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dami_.gymfuture.Model.Exercise;
import com.example.dami_.gymfuture.R;

import java.util.List;

public class ExercisesAdapter extends RecyclerView.Adapter<ExercisesAdapter.ViewHolder>
{
    private List<Exercise> listExercises;
    private LayoutInflater inflater;
    public ItemClickListener clickListener;
    private Context context;

    public ExercisesAdapter(Context context, List<Exercise> listExercises){
        this.listExercises = listExercises;
        this.inflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = this.inflater.inflate(R.layout.row_exercise, viewGroup, false );
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        Exercise exercise = this.listExercises.get(i);
        viewHolder.eName.setText(exercise.getName());

        System.out.println("IMAGE URL: " + exercise.getImageUrl());

        Glide.with(context).load(exercise.getImageUrl()).into(viewHolder.eImage);
    }

    @Override
    public int getItemCount() {
        return this.listExercises.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView eName;
        ImageView eImage;

         public ViewHolder(@NonNull View itemView) {
             super(itemView);
             this.eName = itemView.findViewById(R.id.txt_eName);
             this.eImage = itemView.findViewById(R.id.img_exercise);

             itemView.setOnClickListener(this);
         }

         @Override
         public void onClick(View v) {
                if(clickListener != null) clickListener.onItemClick(v , getAdapterPosition());
         }
     }

     public void setClickListener(ItemClickListener itemClickListener){
        this.clickListener = itemClickListener;
     }

    public Exercise getItem(int id) {
        return listExercises.get(id);
    }

    public void addItems(List<Exercise> exercises) {
        this.listExercises = exercises;
        notifyDataSetChanged();
    }

     public interface ItemClickListener{
        void onItemClick(View view , int position);
     }
}
