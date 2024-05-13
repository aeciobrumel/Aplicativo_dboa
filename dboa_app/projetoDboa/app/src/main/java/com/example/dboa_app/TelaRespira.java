package com.example.dboa_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.VideoView;

public class TelaRespira extends AppCompatActivity {

    VideoView vdv;
    int contVideo = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_respira);

        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        );
        MainActivity.mp.start();
        MainActivity.mp.setVolume(MainActivity.volume(15),MainActivity.volume(15));





        vdv = findViewById(R.id.videoRespira);

        Uri caminho = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.respira);
        vdv.setVideoURI(caminho);

        vdv.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

            @Override
            public void onCompletion(MediaPlayer mp) {
                contVideo++;
                if( contVideo < 3 ){
                    vdv.seekTo(0);

                    vdv.start();
                } else {
                    Intent i = new Intent(TelaRespira.this, Visualizador.class);
                    startActivity(i);
                }

            }
        });


        vdv.start();

    }

    public void onBackPressed() {
        // não chame o super desse método



    }



}

