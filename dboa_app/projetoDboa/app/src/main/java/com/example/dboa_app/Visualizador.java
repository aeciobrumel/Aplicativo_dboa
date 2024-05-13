package com.example.dboa_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;

public class Visualizador extends AppCompatActivity implements TextToSpeech.OnInitListener {

    private Button btnProximo,btnEncerrar;
    private TextView texto;
    private ImageButton imgBtn;
    private CartaoDAO objcartaoDAO;
    private List<Cartao> cards;
    int i = 0;
    int tamanho;
    Cartao cardviewer;

    private TextToSpeech textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizador);

        btnProximo = findViewById(R.id.proximo);
        btnEncerrar = findViewById(R.id.encerrar);
        texto = findViewById(R.id.edt_texto);
        imgBtn = findViewById(R.id.imagebtn);

        objcartaoDAO = new CartaoDAO(this);
        cards = objcartaoDAO.consultarCartoesBD();
        tamanho = cards.size();

        cardviewer = cards.get(i);
        texto.setText(cardviewer.getTexto());

        // Inicialize o TextToSpeech
        textToSpeech = new TextToSpeech(this, this);

        btnProximo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = (i + 1) % tamanho;  // Use o operador de módulo para criar um loop
                cardviewer = cards.get(i);
                texto.setText(cardviewer.getTexto());
            }
        });
        btnEncerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.mp.stop();
                Intent i = new Intent(Visualizador.this, MainActivity.class);
                startActivity(i);
            }
        });
        imgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (textToSpeech != null) {
                    // Use o método speak para converter o texto para áudio e reproduzi-lo
                    textToSpeech.speak(cardviewer.getTexto(), TextToSpeech.QUEUE_FLUSH, null, null);
                }
            }
        });



    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            // Configure a linguagem, se necessário
            int result = textToSpeech.setLanguage(Locale.getDefault());

            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TextToSpeech", "Language not supported");
            }else {
                // Defina a velocidade da fala para um valor menor que 1.0 para diminuir a velocidade
                float speechRate = 0.9f;  // Altere esse valor conforme necessário
                textToSpeech.setSpeechRate(speechRate);
            }
        } else {
            Log.e("TextToSpeech", "Initialization failed");
        }
    }

    @Override
    protected void onDestroy() {
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
        super.onDestroy();
    }
}
