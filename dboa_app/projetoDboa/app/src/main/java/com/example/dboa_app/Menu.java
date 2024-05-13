package com.example.dboa_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Menu extends AppCompatActivity {
    private Button btn_editar_card, btn_criar_card, btn_inicio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        btn_criar_card = findViewById(R.id.criar_card);
        btn_inicio = findViewById(R.id.inicio);
        btn_editar_card = findViewById(R.id.editar_card);

        btn_inicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Menu.this, MainActivity.class);
                startActivity(i);

            }
        }
        );
        btn_criar_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Menu.this, Criar_cartao.class);
                startActivity(i);

            }
        });
        btn_editar_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Menu.this, Editar_Cartao.class);
                startActivity(i);

            }
        });




    }
}