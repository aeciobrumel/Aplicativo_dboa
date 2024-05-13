package com.example.dboa_app;

import java.io.Serializable;

public class Cartao implements Serializable{
    private int id;
    private String titulo;
    private String texto;
    private String midia;
    private String audio;


    public Cartao() {
    }

    public Cartao(String titulo, String texto) {
        this.titulo = titulo;
        this.texto = texto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getMidia() {
        return midia;
    }

    public void setMidia(String midia) {
        this.midia = midia;
    }

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }

    @Override
    public String toString() {
         return
                "\n-" + titulo
                 +"\n"+texto+"\n"
                ;
    }
}
