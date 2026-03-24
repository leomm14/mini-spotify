package com.insper.mini_spotify.musica;

import com.insper.mini_spotify.album.Album;
import com.insper.mini_spotify.artista.Artista;

public class Musica {

    private Long id;
    private String titulo;
    private Integer duracaoSegundos;
    private Integer numeroFaixa;
    private Album album;
    private Artista artista;
    private Long totalReproducoes;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getDuracaoSegundos() {
        return duracaoSegundos;
    }
    public void setDuracaoSegundos(Integer duracaoSegundos) {
        this.duracaoSegundos = duracaoSegundos;
    }

    public Integer getNumeroFaixa() {return numeroFaixa;}
    public void setNumeroFaixa(Integer numeroFaixa) {this.numeroFaixa = numeroFaixa;}

    public Album getAlbum() {
        return album;
    }
    public void setAlbum(Album album) {
        this.album = album;
    }

    public Artista getArtista() {
        return artista;
    }
    public void setArtista(Artista artista) {
        this.artista = artista;
    }

    public Long getTotalReproducoes() {
        return totalReproducoes;
    }
    public void setTotalReproducoes(Long totalReproducoes) {
        this.totalReproducoes = totalReproducoes;
    }

}
