package com.example.dboa_app;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class CartaoDAO {
    private SQLiteDatabase bd_deck;
    private ConexaoBD conexaoBD;

    public CartaoDAO(Context context){
        this.conexaoBD = new ConexaoBD(context);
        this.bd_deck = conexaoBD.getReadableDatabase();
    }

    public ContentValues gerarValores(Cartao objCartao){
        ContentValues values = new ContentValues();

        values.put("Titulo",objCartao.getTitulo());
        values.put("Texto",objCartao.getTexto());


        return values;
    }
    public void cadastrarCartaoBD(Cartao objCartao){

        this.bd_deck.insert("tb_cartao",null,this.gerarValores(objCartao));
    }

    public void alterarCartaoBD(Cartao objCartao){
        this.bd_deck.update("tb_cartao",
                this.gerarValores(objCartao),"id = ?",
                new String[]{String.valueOf(objCartao.getId())});
    }
    public List<Cartao> consultarCartoesBD(){
        List<Cartao> listaDeCartoes = new ArrayList<>();

        Cursor registros = this.bd_deck.query("tb_cartao",new String[] {"id","titulo","texto","midia","audio"},
                null,null,null,null,null);
        while (registros.moveToNext()){
            Cartao objCartao = new Cartao();

            objCartao.setId(registros.getInt(0));
            objCartao.setTitulo(registros.getString(1));
            objCartao.setTexto(registros.getString(2));

            listaDeCartoes.add(objCartao);
        }
        return listaDeCartoes;
    }
    public void excluirCartaoBD(Cartao objCartao){
        this.bd_deck.delete("tb_cartao","id = ?",new String[]{String.valueOf(objCartao.getId())});
    }



}
