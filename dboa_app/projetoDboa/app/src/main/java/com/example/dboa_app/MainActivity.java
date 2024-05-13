package com.example.dboa_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    public Button btniniciar, btnConfig;
    public static MediaPlayer mp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btniniciar = findViewById(R.id.iniciar);
        btnConfig = findViewById(R.id.btnConfig);

        mp = MediaPlayer.create(this, R.raw.fundo);


        btniniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, TelaRespira.class);
                startActivity(i);
            }
        });
        btnConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Menu.class);
                startActivity(i);
            }
        });


    }


    public static float volume(int volumeDesejado){
        return (volumeDesejado*1f)/(100f);
    }

    public void onBackPressed() {
        // não chame o super desse método
    }
}