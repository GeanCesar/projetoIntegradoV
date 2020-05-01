package com.example.projetointegrado.historico;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetointegrado.R;
import com.example.projetointegrado.Uteis;
import com.example.projetointegrado.modelos.ModeloRecyclerViewHistorico;

import java.util.ArrayList;

public class AdapterRecyclerView extends RecyclerView.Adapter<HolderRecyclerView> {

    Context c;
    ArrayList<ModeloRecyclerViewHistorico> modelo;
    FragmentManager fragmentManager;

    public AdapterRecyclerView(Context c, ArrayList<ModeloRecyclerViewHistorico> modelo, FragmentManager fragmentManager) {
        this.c = c;
        this.modelo = modelo;
        this.fragmentManager = fragmentManager;
    }

    @NonNull
    @Override
    public HolderRecyclerView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_historico, null);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(lp);
        return new HolderRecyclerView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderRecyclerView holder, int position) {
        holder.tvHeader.setText((modelo.get(position).getReserva().getSala().isLaboratorio() ? "Laboratório " : "Sala ") + modelo.get(position).getReserva().getSala().getnSala());
        holder.tvDataHistorico.setText("Data: " + Uteis.converteDataHora(modelo.get(position).getReserva().getData()));

        switch (modelo.get(position).getReserva().getStatus()){
            case 1: // Aprovado
                holder.tvStatus.setBackgroundColor(ContextCompat.getColor(c, R.color.verde));
                break;
            case 2: // Pendente
                holder.tvStatus.setBackgroundColor(ContextCompat.getColor(c, R.color.azul_projeto));
                break;
            case 3: // Recusado
                holder.tvStatus.setBackgroundColor(ContextCompat.getColor(c, R.color.vermelho));
                break;
        }
        holder.llLinhaHistorico.setTag(modelo.get(position).getReserva());
    }

    @Override
    public int getItemCount() { return modelo.size(); }

}
