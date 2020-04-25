package com.example.projetointegrado.aprovarReserva;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.projetointegrado.R;
import com.example.projetointegrado.modelos.Reservas;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class DialogAprovar extends BottomSheetDialogFragment implements View.OnClickListener {

    private BottomSheetListener mListener;
    private Button btAprovar;
    private Button btRecusar;
    private Button btCancelar;

    private Reservas reserva;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_aprovar, container, false);
        btAprovar = (Button) v.findViewById(R.id.bt_AprovarFragment);
        btRecusar = (Button) v.findViewById(R.id.bt_RecusarFragment);
        btCancelar = (Button) v.findViewById(R.id.bt_CancelarFragment);

        btAprovar.setOnClickListener(this);
        btRecusar.setOnClickListener(this);
        btCancelar.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == btAprovar.getId()){
            mListener.onButtonClicker("Aprovado", reserva);
        }else if(v.getId() == btRecusar.getId()){
            mListener.onButtonClicker("Recusado", reserva);
        }else if(v.getId() == btCancelar.getId()){
            mListener.onButtonClicker("Cancelado", reserva);
        }
        dismiss();
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
