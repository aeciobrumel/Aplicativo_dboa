package com.example.dboa_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Criar_cartao extends AppCompatActivity {

    private EditText edtTitulo, edtTexto;
    private Button btnCriar, btnListar, btnInicio;
    private CartaoDAO objCartaoDAO;
    private Cartao objCartao = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_cartao);

        edtTitulo = findViewById(R.id.edtTitulo);
        edtTexto = findViewById(R.id.edtTexto);
        btnCriar = findViewById(R.id.salvar);
        btnListar = findViewById(R.id.cancelar);
        btnInicio = findViewById(R.id.inicio);
        edtTitulo.requestFocus();
        objCartaoDAO = new CartaoDAO(this);




        Intent i = getIntent();
        if (i.hasExtra("cartao")){
            objCartao = (Cartao) i.getSerializableExtra("cartao");

            edtTitulo.setText(objCartao.getTitulo());
            edtTexto.setText(objCartao.getTexto());
            btnCriar.setText("Alterar");
        }

        btnCriar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (objCartao == null) {
                    Cartao objCartao = new Cartao();

                    objCartao.setTitulo(edtTitulo.getText().toString());
                    objCartao.setTexto(edtTexto.getText().toString());

                    objCartaoDAO.cadastrarCartaoBD(objCartao);
                    Toast.makeText(Criar_cartao.this, "Criado com sucesso! ", Toast.LENGTH_LONG).show();
                } else {
                    objCartao.setTitulo(edtTitulo.getText().toString());
                    objCartao.setTexto(edtTexto.getText().toString());

                    objCartaoDAO.alterarCartaoBD(objCartao);
                    Toast.makeText(Criar_cartao.this, "Alterado com sucesso! ", Toast.LENGTH_LONG).show();
                }
                edtTitulo.setText("");
                edtTexto.setText("");
                edtTitulo.requestFocus();
            }
        });

        btnListar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Criar_cartao.this, Editar_Cartao.class);
                startActivity(i);
            }
        });
        btnInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Criar_cartao.this, MainActivity.class);
                startActivity(i);
            }
        });
    }

}



