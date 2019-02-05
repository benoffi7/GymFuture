package com.example.dami_.gymfuture.app;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.dami_.gymfuture.Model.Exercise;
import com.example.dami_.gymfuture.R;

import java.util.List;

public class AlimentosAdapter extends RecyclerView.Adapter<AlimentosAdapter.ListaPaisesViewHolder> {

    private Activity mContext;
    private List<Exercise>seleccionableList;

    ItemSeleccionInterface mAdapterInterface;

    public interface ItemSeleccionInterface
    {
        public void onClickItem(Exercise item);

    }

    public AlimentosAdapter(Activity context, List<Exercise>seleccionableList, ItemSeleccionInterface itemSeleccionInterface)
    {
        mContext = context;
        this.seleccionableList = seleccionableList;
        this.mAdapterInterface = itemSeleccionInterface;
    }

    @NonNull
    @Override
    public ListaPaisesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_ejercicio, parent, false);

        return new AlimentosAdapter.ListaPaisesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListaPaisesViewHolder holder, int position) {
        holder.bindMascota(seleccionableList.get(position));
    }

    @Override
    public int getItemCount() {
        return seleccionableList.size();
    }

    public class ListaPaisesViewHolder extends RecyclerView.ViewHolder {

        public TextView textView_nombre;
        LinearLayout ll_root;

        public ListaPaisesViewHolder(View itemView) {
            super(itemView);

            textView_nombre = itemView.findViewById(R.id.textView);

            ll_root = itemView.findViewById(R.id.ll_root);
        }

        public void bindMascota(final Exercise exercise)
        {
            textView_nombre.setText(exercise.getDescription());
            ll_root.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    mAdapterInterface.onClickItem(exercise);
                }
            });
        }
    }
}
