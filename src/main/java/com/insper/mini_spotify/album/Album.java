package com.insper.mini_spotify.album;

import com.insper.mini_spotify.artista.Artista;

import java.time.LocalDate;

public class Album {

    private Long id;
    private String titulo;
    private LocalDate dataLancamento;
    private Artista artista;

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

    public LocalDate getDataLancamento() {
        return dataLancamento;
    }
    public void setDataLancamento(LocalDate dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public Artista getArtista() {
        return artista;
    }
    public void setArtista(Artista artista) {
        this.artista = artista;
    }

}
