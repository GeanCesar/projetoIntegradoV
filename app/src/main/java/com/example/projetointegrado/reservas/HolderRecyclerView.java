package com.example.projetointegrado.reservas;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetointegrado.R;

public class HolderRecyclerView extends RecyclerView.ViewHolder {

    TextView tvHeader;
    TextView tvContent;
    LinearLayout llLinha;

    public HolderRecyclerView(@NonNull View itemView) {
        super(itemView);

        llLinha = itemView.findViewById(R.id.llLinhaReservas);
        tvHeader = itemView.findViewById(R.id.tvHeadItem);
        tvContent = itemView.findViewById(R.id.tvContentItem);
    }
}

