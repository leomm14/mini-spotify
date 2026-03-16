package com.insper.mini_spotify;

public class Artista {

    private long id;
    private String nome;
    private String generoMusical;
    private String paisOrigem;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getGeneroMusical() {
        return generoMusical;
    }
    public void setGeneroMusical(String generoMusical) {
        this.generoMusical = generoMusical;
    }

    public String getPaisOrigem() {
        return paisOrigem;
    }
    public void setPaisOrigem(String paisOrigem) {
        this.paisOrigem = paisOrigem;
    }

}
