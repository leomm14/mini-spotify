package com.insper.mini_spotify;

import java.time.LocalDate;

public class Musica {

    private long id;
    private String titulo;
    private int duracaoSegundos;
    private String album;
    private String artista;
    private long totalReproducoes;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getDuracaoSegundos() {
        return duracaoSegundos;
    }
    public void setDuracaoSegundos(int duracaoSegundos) {
        this.duracaoSegundos = duracaoSegundos;
    }

    public String getAlbum() {
        return album;
    }
    public void setAlbum(String album) {
        this.album = album;
    }

    public String getArtista() {
        return artista;
    }
    public void setArtista(String artista) {
        this.artista = artista;
    }

    public long getTotalReproducoes() {
        return totalReproducoes;
    }
    public void setTotalReproducoes(long totalReproducoes) {
        this.totalReproducoes = totalReproducoes;
    }

}
