package com.example.dboa_app;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SearchView;


import java.util.ArrayList;
import java.util.List;


public class Editar_Cartao extends AppCompatActivity {

    private ListView lstCartao;
    private ImageButton criarIMGbtn;
    private  CartaoDAO objCartaoDAO;
    private SearchView icConsultar;
    private List<Cartao> todosOsCartoes;

    private List<Cartao> CartoesFiltrados = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_cartao);

        lstCartao = findViewById(R.id.lstCartoes);
        objCartaoDAO = new CartaoDAO(this);

        todosOsCartoes = objCartaoDAO.consultarCartoesBD();
        CartoesFiltrados.addAll(todosOsCartoes);
        criarIMGbtn = findViewById(R.id.criarbtn);

        ArrayAdapter adp = new ArrayAdapter(this, android.R.layout.simple_list_item_1,CartoesFiltrados);
        lstCartao.setAdapter(adp);

        registerForContextMenu(lstCartao);




        criarIMGbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Editar_Cartao.this, Criar_cartao.class);
                startActivity(i);
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater mi = getMenuInflater();
        mi.inflate(R.menu.menu_principal,menu);
        icConsultar = (SearchView) menu.findItem(R.id.icConsultar).getActionView();

        icConsultar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {

                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                procurarPorTitulo(s);
                return true;
            }
        });
        return true;
    }






    public void procurarPorTitulo(String nome){
        CartoesFiltrados.clear();

        for(int i = 0;i < todosOsCartoes.size();i++){
            if(todosOsCartoes.get(i).getTitulo().toLowerCase().contains(nome.toLowerCase())){
                CartoesFiltrados.add(todosOsCartoes.get(i));
            }
        }
        lstCartao.invalidateViews();
    }
    public void abrirCadastrar(MenuItem item){
        Intent i = new Intent(Editar_Cartao.this,Criar_cartao.class);
        startActivity(i);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater mi = getMenuInflater();
        mi.inflate(R.menu.menu_item,menu);
    }

    public void excluirCartao(MenuItem item){
        AdapterView.AdapterContextMenuInfo menuInfo =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        Cartao objCartaoDeletado = CartoesFiltrados.get(menuInfo.position);

        AlertDialog confirmacao = new AlertDialog
                .Builder(this)
                .setIcon(R.drawable.ic_atecao)
                .setTitle("Atenção!!")
                .setMessage("Deseja realmente excluir esse Cartão?")
                .setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        todosOsCartoes.remove(objCartaoDeletado);
                        CartoesFiltrados.remove(objCartaoDeletado);
                        objCartaoDAO.excluirCartaoBD(objCartaoDeletado);
                        lstCartao.invalidateViews();
                    }
                }).create();
        confirmacao.show();
    }
        public void alterarCartao(MenuItem item){
            AdapterView.AdapterContextMenuInfo menuInfo =
                    (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            Cartao objCartaoAlterado = CartoesFiltrados.get(menuInfo.position);
            Intent i = new Intent(this,Criar_cartao.class);
            i.putExtra("cartao",objCartaoAlterado);
            startActivity(i);
    }




}