package com.pharmacy.martin.lab01c2016;

import android.net.wifi.p2p.WifiP2pManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener, View.OnClickListener{

    protected TextView tvDias;
    protected SeekBar sbCantDias;
    protected Button btnConfirmar;
    protected CheckBox chBoxRenovar;
    protected EditText tfImporte;
    protected TextView tvNumRendimiento;
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
        tvInfoConfirmacion = (TextView) findViewById(R.id.tvInfoConfirmacion);
        tvNumRendimiento = (TextView) findViewById(R.id.tvNumRendimiento);


        /**inicializacion de los escuchadores de eventos*/
        sbCantDias.setOnSeekBarChangeListener(this);
        btnConfirmar.setOnClickListener(this);
        tvDias.setText("Dias: " + sbCantDias.getProgress());
        tfImporte.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(tfImporte!=null){
                    tvNumRendimiento.setText("$"+String.valueOf(calcularGanancias()));
                }
                else {
                    tvNumRendimiento.setText("$0.0");
                }
            }
        });
    }

    /**Manejadores de evento de la SeekBar*/
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        tvDias.setText("Dias: " + sbCantDias.getProgress());
    }
    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }
    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        tvDias.setText("Dias: " + sbCantDias.getProgress());
        tvNumRendimiento.setText("$"+String.valueOf(calcularGanancias()));
    }

    /**Manejador de evento del Boton Confirmar*/
    @Override
    public void onClick(View v) {
        ArrayList<String> msgList = new ArrayList<>();
        if(controlarDatos(msgList)){
            tvInfoConfirmacion.setTextColor(ContextCompat.getColor(this, R.color.correcto));
            tvInfoConfirmacion.setTextSize(18);
            tvInfoConfirmacion.setText(String.valueOf("El plazo fijo ha sido confirmadio, recibira $" + calcularGanancias() + " al finalizar el plazo"));
        }
        else{
            tvInfoConfirmacion.setTextColor(ContextCompat.getColor(this, R.color.error));
            tvInfoConfirmacion.setTextSize(18);
            tvInfoConfirmacion.setText(String.valueOf(TextUtils.join("\n",msgList)));

        }
    }

    /*Metodo para calcular las ganancias*/
    private double calcularGanancias(){

        /*Definimos el formato de salida*/
        DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols();
        otherSymbols.setDecimalSeparator('.');
        DecimalFormat resultado = new DecimalFormat("###.##",otherSymbols);
        /*Definiciones de variables*/
        double interes, monto;
        float tasa,dias;
        EditText auxMonto = (EditText) findViewById(R.id.tfImporte);
        SeekBar auxDias = (SeekBar) findViewById(R.id.sbCantDias);
        if (auxMonto.getText() != null) {
            monto = Integer.parseInt(auxMonto.getText().toString());
        }
        else{
            monto = 0;
        }
        dias = auxDias.getProgress();
        if(monto < 5000){
            if(dias<30){
                tasa=getResources().getFraction(R.fraction.interes5000_Menor30,1,1);
            }
            else{
                tasa=getResources().getFraction(R.fraction.interes5000_Mayor30,1,1);
            }
        }else {
            if(monto < 99999){
                if(dias<30){
                    tasa=getResources().getFraction(R.fraction.interes99999_Menor30,1,1);
                }
                else{
                    tasa=getResources().getFraction(R.fraction.interes99999_Mayor30,1,1);
                }
            }
            else{
                if(dias<30){
                    tasa=getResources().getFraction(R.fraction.interesMayor99999_Menor30,1,1);
                }
                else{
                    tasa=getResources().getFraction(R.fraction.interesMayor99999_Mayor30,1,1);
                }
            }
        }

        interes = monto * ((Math.pow((1+tasa),(dias/360)))-1);

        return Double.parseDouble(resultado.format(interes));

    }

    /*Metodo para comprobar que los datos ingresados sean correctos*/
    private boolean controlarDatos(ArrayList<String> msgList){
        boolean state = true;
        EditText mail = (EditText)findViewById(R.id.editTextEmail);
        Pattern patMail = Pattern.compile("^[\\w-]+(\\.[\\w-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher matEmail = patMail.matcher(mail.getText().toString());
        if(!matEmail.find()){
            //Si no es un mail valido
            msgList.add(getResources().getText(R.string.errEmail).toString());
            state = false;
        }

        EditText cuit = (EditText)findViewById(R.id.tfCuit);
        Pattern patCuit = Pattern.compile("^(20|23|27)\\d{7,8}\\d$");
        Matcher matCuit = patCuit.matcher(cuit.getText().toString());
        if(!matCuit.find()){
            //Si no es un cuit valido
            msgList.add(getResources().getText(R.string.errCuit).toString());
            state = false;
        }
        return state;
    }

}
