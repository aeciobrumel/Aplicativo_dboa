package com.example.dboa_app;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ConexaoBD extends SQLiteOpenHelper {
    private static final String name = "bd_deck";
    private static final int version = 1;


    public ConexaoBD(@Nullable Context context) {
        super(context, name, null, version);
    }



    @Override
    public void onCreate(SQLiteDatabase bd_deck) {
        bd_deck.execSQL("create table tb_cartao(id Integer not null primary key autoincrement," +
                "titulo varchar(50) not null,texto varchar(255), midia varchar(255),audio varchar(255))");

        inserirCartoesIniciais(bd_deck);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    // Método para inserir cartões iniciais
    private void inserirCartoesIniciais(SQLiteDatabase bd_deck) {
        ContentValues values = new ContentValues();


        values.put("titulo", "Veja");
        values.put("texto", "Olhe ao seu redor e identifique cinco coisas que você pode ver.");
        bd_deck.insert("tb_cartao", null, values);

        values.clear();
        values.put("titulo", "Ouça");
        values.put("texto", "Preste atenção aos sons ao seu redor e identifique quatro coisas que você pode ouvir.");
        bd_deck.insert("tb_cartao", null, values);

        values.clear();
        values.put("titulo", "Sinta");
        values.put("texto", "Sintonize suas sensações táteis e identifique três coisas que você pode sentir ao tocar.");
        bd_deck.insert("tb_cartao", null, values);

        values.clear();
        values.put("titulo", "Cheire");
        values.put("texto", "Concentre-se nos cheiros ao seu redor e identifique dois aromas distintos.");
        bd_deck.insert("tb_cartao", null, values);

        values.clear();
        values.put("titulo", "Gosto");
        values.put("texto", "Explore o sentido do paladar identificando uma coisa que você pode saborear.");
        bd_deck.insert("tb_cartao", null, values);
    }
}

    //