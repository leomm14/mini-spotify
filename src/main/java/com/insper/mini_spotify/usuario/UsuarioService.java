package com.insper.mini_spotify.usuario;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import  java.util.HashMap;

@Service
public class UsuarioService {

    private HashMap<Long, Usuario> usuarios = new HashMap<>();

    public Usuario cadastrarUsuario(Usuario usuario) {

        if (usuario.getId() == null || usuario.getNome() == null || usuario.getEmail() == null
                || usuario.getTipoPlano() == null || usuario.getAtivo() == null || usuario.getDataCriacao() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário não pode ser nulo");
        }
        usuarios.put(usuario.getId(), usuario);
        return usuario;

    }

    public Collection<Usuario> listarUsuarios() {return usuarios.values();}

    public Usuario getUsuario(Long id) {
        Usuario usuario = usuarios.get(id);
        if (usuario == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado");
        }
        return usuario;
    }

    public Usuario updateUsuario(Long id, Usuario usuario) {
        Usuario usuarioAntigo = usuarios.get(id);
        if (usuarioAntigo == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado");
        }
        if (usuario.getNome() != null) {
            usuarioAntigo.setNome(usuario.getNome());
        }
        if (usuario.getEmail() != null) {
            usuarioAntigo.setEmail(usuario.getEmail());
        }
        if (usuario.getTipoPlano() != null) {
            usuarioAntigo.setTipoPlano(usuario.getTipoPlano());
        }
        if (usuario.getAtivo() != null) {
            usuarioAntigo.setAtivo(usuario.getAtivo());
        }
        if (usuario.getDataCriacao() != null) {
            usuarioAntigo.setDataCriacao(usuario.getDataCriacao());
        }
        return usuarioAntigo;
    }

    public void deleteUsuario(Long id) {
        Usuario usuario = getUsuario(id);
        usuarios.remove(id);
    }

}

