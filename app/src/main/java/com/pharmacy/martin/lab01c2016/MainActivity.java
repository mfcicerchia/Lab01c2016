package com.pharmacy.martin.lab01c2016;

import android.net.wifi.p2p.WifiP2pManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener, View.OnClickListener {




    protected TextView tvDias;
    protected SeekBar sbCantDias;
    protected Button btnConfirmar;
    protected CheckBox chBoxRenovar;
    protected EditText tfImporte;
    protected EditText tfRendiminto;
    protected TextView tvInfoConfirmacion;


    protected double resultado;
    protected double importe;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**Obtencion de los id's de los elementos de la Activity*/
        tvDias = (TextView) findViewById(R.id.tvDias);
        sbCantDias = (SeekBar) findViewById(R.id.sbCantDias);
        btnConfirmar = (Button) findViewById(R.id.btnConfirmar);
        chBoxRenovar = (CheckBox) findViewById(R.id.chBoxRenovar);
        tfImporte = (EditText) findViewById(R.id.tfImporte);
        tfRendiminto = (EditText) findViewById(R.id.tfRendimiento);
        tvInfoConfirmacion = (TextView) findViewById(R.id.tvInfoConfirmacion);


        /**inicializacion de los escuchadores de eventos*/
        sbCantDias.setOnSeekBarChangeListener(this);
        btnConfirmar.setOnClickListener((View.OnClickListener) this);


        tvDias.setText("Dias: " + sbCantDias.getProgress());
    }


    /**Manejadores de evento de la SeekBar*/
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        tvDias.setText("Dias: " + sbCantDias.getProgress());


        /**cambio el rendimiento mientras cambio la cantidad de dias*/
        if (importe > 0) {
            tfRendiminto.setText(String.valueOf(resultado));
        } else {
            tfRendiminto.setText(String.valueOf(0));
        }

    }
    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }


    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        tvDias.setText("Dias: " + sbCantDias.getProgress());
    }


    /**Manejador de evento del Boton Confirmar*/
    @Override
    public void onClick(View v) {
        tvInfoConfirmacion.setText(String.valueOf("El plazo fijo ha sido confirmadio, recibira $$$$ al finalizar el plazo"));
    }


}
