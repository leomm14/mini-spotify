package com.insper.mini_spotify;

import java.time.LocalDateTime;

public class Usuario {

    private long id;
    private String nome;
    private String email;
    public enum TipoPlano {FREE, PREMIUM}
    private TipoPlano tipoPlano;
    private boolean ativo;
    private LocalDateTime dataCriacao;

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

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public TipoPlano getTipoPlano() {return tipoPlano;}
    public void setTipoPlano(TipoPlano tipoPlano) {
        this.tipoPlano = tipoPlano;
    }

    public boolean getAtivo() {
        return ativo;
    }
    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }
    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

}