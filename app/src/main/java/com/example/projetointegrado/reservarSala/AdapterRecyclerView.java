package com.example.projetointegrado.reservarSala;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetointegrado.R;
import com.example.projetointegrado.modelos.ModeloRecyclerViewReservar;
import com.example.projetointegrado.modelos.Sala;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class AdapterRecyclerView extends RecyclerView.Adapter<HolderRecyclerView>{

    Context c;
    ArrayList<ModeloRecyclerViewReservar> modelo;
    FragmentManager fragmentManager;

    public AdapterRecyclerView(Context c, ArrayList<ModeloRecyclerViewReservar> modelo, FragmentManager fragmentManager) {
        this.c = c;
        this.modelo = modelo;
        this.fragmentManager = fragmentManager;
    }

    @NonNull
    @Override
    public HolderRecyclerView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_reservar, null);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(lp);
        return new HolderRecyclerView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderRecyclerView holder, int position) {
        holder.tvHeader.setText(modelo.get(position).getHeader());
        holder.tvHeader.setTag(modelo.get(position).getSala());
        holder.tvHeader.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Sala s = (Sala) view.getTag();

                ((ReservarActivity)c).valores.setSala(s);
                ((ReservarActivity)c).chamaCalendario();
            }
        });
    }

    @Override
    public int getItemCount() { return modelo.size(); }
}
