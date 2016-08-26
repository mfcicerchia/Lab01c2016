package com.pharmacy.martin.lab01c2016;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    TextView tvDias;
    SeekBar sbCantDias;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvDias=(TextView) findViewById(R.id.tvDias);
        sbCantDias=(SeekBar) findViewById(R.id.sbCantDias);
        tvDias.setText("Dias: "+ sbCantDias.getProgress());

        sbCantDias.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvDias.setText("Dias: "+ sbCantDias.getProgress());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                tvDias.setText("Dias: "+ sbCantDias.getProgress());
            }
        });


    }



/*    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        tvDias.setText("Dias: "+ sbCantDias.getProgress());
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

       *//* textView.setText("Covered: " + progress + "/" + seekBar.getMax());*//*
        tvDias.setText("Dias: "+ sbCantDias.getProgress());
    }

    public void OnClickListener(View view){

    }*/
}
