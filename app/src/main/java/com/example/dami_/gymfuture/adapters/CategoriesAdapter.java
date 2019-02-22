package com.example.dami_.gymfuture.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dami_.gymfuture.Model.Category;
import com.example.dami_.gymfuture.Model.Exercise;
import com.example.dami_.gymfuture.R;

import java.util.List;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.ViewHolder>
{
    private List<Category> list;
    private LayoutInflater inflater;
    public ItemClickListener clickListener;
    private Context context;

    public CategoriesAdapter(Context context, List<Category> list){
        this.list = list;
        this.inflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = this.inflater.inflate(R.layout.row_category, viewGroup, false );
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        Category category = this.list.get(i);
        viewHolder.name.setText(category.getName());

         Glide.with(context).load(category.getImage_url()).into(viewHolder.image);
    }

    @Override
    public int getItemCount() {
        return this.list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView name;
        ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.name = itemView.findViewById(R.id.txt_nameCategory);
            this.image = itemView.findViewById(R.id.img_category);
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

    public Category getItem(int id) {
        return list.get(id);
    }

    public void addItems(List<Category> categories) {
        this.list = categories;
        notifyDataSetChanged();
    }

    public interface ItemClickListener{
        void onItemClick(View view , int position);
    }
}
