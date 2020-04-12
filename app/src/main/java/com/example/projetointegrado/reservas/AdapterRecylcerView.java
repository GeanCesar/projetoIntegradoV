package com.example.projetointegrado.reservas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetointegrado.R;
import com.example.projetointegrado.modelos.ModeloRecyclerView;

import java.util.ArrayList;

public class AdapterRecylcerView extends RecyclerView.Adapter<HolderRecyclerView> {

    Context c;
    ArrayList<ModeloRecyclerView> modelo;

    public AdapterRecylcerView(Context c, ArrayList<ModeloRecyclerView> modelo) {
        this.c = c;
        this.modelo = modelo;
    }

    @NonNull
    @Override
    public HolderRecyclerView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, null);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(lp);
        return new HolderRecyclerView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderRecyclerView holder, int position) {
        holder.tvHeader.setText(modelo.get(position).getHeader());
        holder.tvContent.setText(modelo.get(position).getContent());
    }

    @Override
    public int getItemCount() {
        return modelo.size();
    }
}
