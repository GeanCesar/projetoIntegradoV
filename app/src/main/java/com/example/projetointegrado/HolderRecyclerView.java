package com.example.projetointegrado;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HolderRecyclerView extends RecyclerView.ViewHolder {

    TextView tvHeader;
    TextView tvContent;

    public HolderRecyclerView(@NonNull View itemView) {
        super(itemView);

        tvHeader = itemView.findViewById(R.id.tvHeadItem);
        tvContent = itemView.findViewById(R.id.tvContentItem);
    }
}

