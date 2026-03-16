package com.insper.mini_spotify;

import java.time.LocalDateTime;

public class Playlist {

    private long id;
    private String nome;
    private boolean publica;
    private LocalDateTime dataCriacao;
    private String usuarios;
    private String musicas;

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

    public boolean getPublica() {
        return publica;
    }
    public void setPublica(boolean publica) {
        this.publica = publica;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }
    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getUsuarios() {
        return usuarios;
    }
    public void setUsuarios(String usuarios) {
        this.usuarios = usuarios;
    }

    public String getMusicas() {
        return musicas;
    }
    public void setMusicas(String musicas) {
        this.musicas = musicas;
    }


}
