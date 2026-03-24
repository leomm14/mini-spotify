package com.insper.mini_spotify.musica;

public class TopMusicasDTO {

    private String titulo;
    private String nomeArtista;
    private Long totalReproducoes;


    public String getTitulo() {
        return titulo;
    }

    public String getNomeArtista() {return nomeArtista;}

    public Long getTotalReproducoes() {return totalReproducoes;}


    public TopMusicasDTO(String titulo, String nomeArtista, Long totalReproducoes) {
        this.titulo = titulo;
        this.nomeArtista = nomeArtista;
        this.totalReproducoes = totalReproducoes;
    }

}