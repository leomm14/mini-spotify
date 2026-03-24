package com.insper.mini_spotify.jam;

import com.insper.mini_spotify.usuario.TipoPlano;
import com.insper.mini_spotify.usuario.Usuario;

import java.time.LocalDateTime;
import java.util.Collection;

//Explicação: Jam é como uma "party" de música, é um "grupo" no Spotify que o usuário pode criar para ouvir música ao mesmo tempo com outras pessoas
public class Jam {

    private Long id;
    private Collection<Usuario> usuarios;
    private LocalDateTime dataCriacao;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Collection<Usuario> getUsuarios() {
        return usuarios;
    }
    public void setUsuarios(Collection<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }
    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }


}
