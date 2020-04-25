package com.example.projetointegrado.aprovarReserva;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetointegrado.R;

public class HolderRecyclerView extends RecyclerView.ViewHolder{

    TextView tvHeader;
    TextView tvProfessorAprovar;
    TextView tvDataAprovar;
    LinearLayout llLinhaAprovar;


    public HolderRecyclerView(@NonNull View itemView) {
        super(itemView);
        tvHeader = itemView.findViewById(R.id.tvHeadItemAprovar);
        tvProfessorAprovar = itemView.findViewById(R.id.tvProfessorAprovar);
        tvDataAprovar = itemView.findViewById(R.id.tvItemDataAprovar);
        llLinhaAprovar = itemView.findViewById(R.id.ll_LinhaAprovar);
    }
}
