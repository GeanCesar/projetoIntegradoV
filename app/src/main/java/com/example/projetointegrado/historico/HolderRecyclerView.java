package com.example.projetointegrado.historico;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetointegrado.R;

public class HolderRecyclerView extends RecyclerView.ViewHolder{

    TextView tvHeader;
    TextView tvDataHistorico;
    TextView tvStatus;
    LinearLayout llLinhaHistorico;


    public HolderRecyclerView(@NonNull View itemView) {
        super(itemView);
        tvHeader = itemView.findViewById(R.id.tvHeadItemHistorico);
        tvDataHistorico = itemView.findViewById(R.id.tvDataItemHistorico);
        tvStatus = itemView.findViewById(R.id.tv_status_item_historico);
        llLinhaHistorico = itemView.findViewById(R.id.ll_LinhaHistorico);
    }
}
