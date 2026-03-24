package com.insper.mini_spotify.playlist;

import com.insper.mini_spotify.musica.Musica;
import com.insper.mini_spotify.usuario.Usuario;

import java.time.LocalDateTime;
import java.util.Collection;

public class Playlist {

    private Long id;
    private String nome;
    private Boolean publica;
    private LocalDateTime dataCriacao;
    private Usuario usuario;
    private Collection<Musica> musicas;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public Boolean getPublica() {
        return publica;
    }
    public void setPublica(Boolean publica) {
        this.publica = publica;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }
    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Collection<Musica> getMusicas() {
        return musicas;
    }
    public void setMusicas(Collection<Musica> musicas) {
        this.musicas = musicas;
    }


}
