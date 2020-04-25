package com.example.projetointegrado.aprovarReserva;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetointegrado.R;
import com.example.projetointegrado.Uteis;
import com.example.projetointegrado.modelos.ModeloRecyclerViewAprovar;
import com.example.projetointegrado.modelos.Reservas;

import java.util.ArrayList;

public class AdapterRecyclerView extends RecyclerView.Adapter<HolderRecyclerView> {

    Context c;
    ArrayList<ModeloRecyclerViewAprovar> modelo;
    FragmentManager fragmentManager;

    public AdapterRecyclerView(Context c, ArrayList<ModeloRecyclerViewAprovar> modelo, FragmentManager fragmentManager) {
        this.c = c;
        this.modelo = modelo;
        this.fragmentManager = fragmentManager;
    }

    @NonNull
    @Override
    public HolderRecyclerView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_aprovar, null);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(lp);
        return new HolderRecyclerView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderRecyclerView holder, int position) {
        holder.tvHeader.setText((modelo.get(position).getReserva().getSala().isLaboratorio() ? "Laboratório " : "Sala ") + modelo.get(position).getReserva().getSala().getnSala());
        holder.tvProfessorAprovar.setText("Professor: " + modelo.get(position).getReserva().getUsuario().getNome());
        holder.tvDataAprovar.setText(Uteis.converteDataHora(modelo.get(position).getReserva().getData()));
        holder.llLinhaAprovar.setTag(modelo.get(position).getReserva());

        holder.llLinhaAprovar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Reservas r = (Reservas) view.getTag();
                DialogAprovar bottomSheet = new DialogAprovar();
                bottomSheet.setReserva(r);
                bottomSheet.show(fragmentManager, "aprovarBottomSheet");
            }
        });
    }

    @Override
    public int getItemCount() { return modelo.size(); }

}
