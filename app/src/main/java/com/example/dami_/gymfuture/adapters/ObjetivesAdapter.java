package com.example.dami_.gymfuture.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.dami_.gymfuture.Model.Objetive;
import com.example.dami_.gymfuture.R;

import java.util.List;

public class ObjetivesAdapter extends RecyclerView.Adapter<ObjetivesAdapter.ViewHolder>
{
    private List<Objetive> list;
    private LayoutInflater inflater;
    public ItemClickListener clickListener;
    private Context context;

    public ObjetivesAdapter(Context context, List<Objetive> list){
        this.list = list;
        this.inflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = this.inflater.inflate(R.layout.row_objetive, viewGroup, false );
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        Objetive objetive = this.list.get(i);
        Glide.with(context).load(objetive.getUrl_image()).into(viewHolder.image);
        viewHolder.imageInfo.setImageResource(R.drawable.drawable_info);
        viewHolder.imageInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "CLICK EN INFO" , Toast.LENGTH_SHORT)
                        .show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView image;
        ImageView imageInfo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.image = itemView.findViewById(R.id.img_objetive);
            this.imageInfo = itemView.findViewById(R.id.id_img_info);
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

    public Objetive getItem(int id) {
        return list.get(id);
    }

    public void addItems(List<Objetive> objetives) {
        this.list = objetives;
        notifyDataSetChanged();
    }

    public interface ItemClickListener{
        void onItemClick(View view , int position);
    }
}
