package com.example.projetointegrado;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.example.projetointegrado.reservarSala.ReservarActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CalendarFragment extends DialogFragment implements CalendarView.OnDateChangeListener {

    private static final String TAG = "CalendarFragment";

    CalendarView cvCalendario;
    Context c;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.calendario, container, false);

        cvCalendario = (CalendarView) view.findViewById(R.id.cvCalendario);
        cvCalendario.setOnDateChangeListener(this);
        return view;
    }


    @Override
    public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {

        String date = dayOfMonth + "/" + month + "/" + year;
        String parts[] = date.split("/");

        int dia = Integer.parseInt(parts[0]);
        int mes = Integer.parseInt(parts[1]);
        int ano = Integer.parseInt(parts[2]);

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, ano);
        calendar.set(Calendar.MONTH, mes);
        calendar.set(Calendar.DAY_OF_MONTH, dia);

        long milliTime = calendar.getTimeInMillis();

        view.setDate(milliTime, true, true);

        ((ReservarActivity)getActivity()).calendario = view;
        //((ReservarActivity)getActivity()).pegaData();
        getDialog().dismiss();
    }
}
