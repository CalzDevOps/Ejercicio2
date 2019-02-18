package com.example.ejercicio2;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    /*
    * 2. Desarrollar una aplicación que sirva como un cronómetro en cuenta descendente.
    * Su valor inicial se ha de poder establecer a partir de una Seekbar que vaya de 0 a 10 minutos.
    *           a. Para iniciar la cuenta se hará a través de un botón.
    *           b. A medida que se actualiza la cuenta, se ha de actualizar la Seekbar
     * */



    TextView tiempo;
    Button boton;
    SeekBar barra;
    boolean contador=false;
    CountDownTimer ct;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tiempo=(TextView)findViewById(R.id.tiempo);
        boton=(Button) findViewById(R.id.button);
        barra=(SeekBar)findViewById(R.id.seekBar);
        barra.setMax(600);
        barra.setProgress(barra.getMax()/2);

        barra.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar myBarr, int progress, boolean fromUser) {

                updateTime(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
    void updateTime(int progreso){

        int sec=progreso % 60;
        int min=Math.round(progreso/60);
        if(sec<=9){
            tiempo.setText(min +":"+"0"+sec);

        }else{
            tiempo.setText(min +":"+sec);

        }
    }
    public void contar(View view){
        if(contador==false){
            contador=true;
            barra.setEnabled(false);
            boton.setText("STOP");
            //myBarr.setVisibility(View.INVISIBLE);
            ct=new CountDownTimer(barra.getProgress()*1000,1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    int time= (int)millisUntilFinished/1000;
                    updateTime(time);
                }

                @Override
                public void onFinish() {
                    reset();
                }
            }.start();
        }else{
            reset();
        }

    }
    void reset(){
        tiempo.setText("10:00");
        barra.setEnabled(true);
        barra.setMax(600);
        boton.setText("Dale chicha");
        ct.cancel();
        contador=false;

    }
    }

