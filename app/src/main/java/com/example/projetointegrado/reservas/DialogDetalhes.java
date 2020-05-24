package com.example.projetointegrado.reservas;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.projetointegrado.R;
import com.example.projetointegrado.Uteis;
import com.example.projetointegrado.modelos.Reservas;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class DialogDetalhes extends BottomSheetDialogFragment implements View.OnClickListener {

    private BottomSheetListener mListener;
    Button btVoltar;
    Reservas reserva;

    TextView tvLaboratorio;
    TextView tvProjetor;
    TextView tvAprovador;
    TextView tvDia;
    TextView tvHora;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_detalhes_reserva, container, false);

        btVoltar = (Button) v.findViewById(R.id.bt_VoltaFragmentDetalhes);
        tvLaboratorio = (TextView) v.findViewById(R.id.tvLaboratorio);
        tvProjetor = (TextView) v.findViewById(R.id.tvProjetor);
        tvAprovador = (TextView) v.findViewById(R.id.tvAprovadoPor);
        tvDia = (TextView) v.findViewById(R.id.tvDiaReserva);
        tvHora = (TextView) v.findViewById(R.id.tvHoraReserva);

        btVoltar.setOnClickListener(this);

        tvLaboratorio.setText((reserva.getSala().isLaboratorio() ? "Laboratorio - " : "Sala - ") + reserva.getSala().getnSala());
        tvProjetor.setText(reserva.getSala().isProjetor() ? "Sim" : "Não");
        tvAprovador.setText(reserva.getAprovador().getNome());
        tvDia.setText(Uteis.converteData(reserva.getData()));
        tvHora.setText(Uteis.converteHora(reserva.getData()));


        return v;
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == btVoltar.getId()){
            dismiss();
        }
    }

    public Reservas getReserva() {
        return reserva;
    }

    public void setReserva(Reservas reserva) {
        this.reserva = reserva;
    }

    public interface BottomSheetListener{
        void onButtonClicker(String text, Reservas reservas);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mListener = (BottomSheetListener) context;
        }catch (ClassCastException e){
            throw new ClassCastException(context.toString() + "  must implement BottomSheetListener");
        }
    }
}
