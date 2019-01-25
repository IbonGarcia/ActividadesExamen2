package com.example.dm2.actividadesexamen;

import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

public class Multimedia extends AppCompatActivity {

    private ImageButton play,pause,rewind,forward;
    private MediaPlayer mediaplayer;
    private TextView cancion, duracion;
    private SeekBar seekBar;
    private double comienzo = 0,fin = 0;
    private int adelantar = 2000, rebobinar = 2000;
    private Handler durationHandler = new Handler();


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multimedia);
        this.setTitle("MediaPlayer");

        cancion = findViewById(R.id.nombreCancion);
        duracion = findViewById(R.id.etiqueta2);
        seekBar = findViewById(R.id.duracion);
        mediaplayer = MediaPlayer.create(this,R.raw.ultimatebattle);
        fin = mediaplayer.getDuration();
        seekBar.setMax((int)fin);
        seekBar.setClickable(false);
    }

    public void play(View view){
        mediaplayer.start();
        cancion.setText("Cancion: "+"Ultimate Battle");
        comienzo = mediaplayer.getCurrentPosition();
        seekBar.setProgress((int)comienzo);
        durationHandler.postDelayed(actualizarSeekBar,100);
    }

    public void pause(View view){
        mediaplayer.pause();
    }

    public void rewind(View view){
            comienzo = comienzo-rebobinar;
            mediaplayer.seekTo((int)comienzo);
    }

    public void forward(View view){

        comienzo = comienzo+adelantar;
        mediaplayer.seekTo((int)comienzo);
    }
    private Runnable actualizarSeekBar = new Runnable(){
        public void run(){
            comienzo = mediaplayer.getCurrentPosition();
            seekBar.setProgress((int)comienzo);
            double tiempoRestante = mediaplayer.getDuration() - comienzo;
            duracion.setText((String.format("Duracion: "+"%d min, %d sec", TimeUnit.MILLISECONDS.toMinutes((long) tiempoRestante), TimeUnit.MILLISECONDS.toSeconds((long) tiempoRestante) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long) tiempoRestante)))));
            durationHandler.postDelayed(this, 100);
        }
    };
}
