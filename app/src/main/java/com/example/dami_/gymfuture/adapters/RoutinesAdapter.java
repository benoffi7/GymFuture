package com.example.dami_.gymfuture.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dami_.gymfuture.Model.Routine;
import com.example.dami_.gymfuture.R;

import java.util.List;

public class RoutinesAdapter extends RecyclerView.Adapter<RoutinesAdapter.ViewHolder> {

    private List<Routine> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private Context context;

    // data is passed into the constructor
    public RoutinesAdapter(Context context, List<Routine> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.context = context;
    }

    // inflates the row layout from xml when needed
    @Override @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.row_routine, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Routine routine = mData.get(position);
        Glide.with(context).load(routine.getUrl_image()).into(holder.imgRoutine);
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void addItems(List<Routine> routines){
        this.mData = routines;
        notifyDataSetChanged();
    }

    // convenience method for getting data at click position
    public Routine getItem(int id) {
        return mData.get(id);
    }

    // allows clicks events to be caught
    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imgRoutine;

        ViewHolder(View itemView) {
            super(itemView);
            imgRoutine = itemView.findViewById(R.id.img_routine);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    public class LoadingViewHolder extends RecyclerView.ViewHolder{
        public ProgressBar progressBar;

        public LoadingViewHolder(@NonNull View itemView) {
            super(itemView);
            this.progressBar = (ProgressBar) itemView.findViewById(R.id.progress_loading);
        }
    }



}