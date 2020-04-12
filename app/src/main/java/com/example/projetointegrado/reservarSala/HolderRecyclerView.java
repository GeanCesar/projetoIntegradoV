package com.example.projetointegrado.reservarSala;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetointegrado.R;

public class HolderRecyclerView extends RecyclerView.ViewHolder{

    TextView tvHeader;


    public HolderRecyclerView(@NonNull View itemView) {
        super(itemView);
        tvHeader = itemView.findViewById(R.id.tvHeadItemReservar);
    }
}
