package com.insper.mini_spotify.jam;

import com.insper.mini_spotify.jam.Jam;
import com.insper.mini_spotify.usuario.Usuario;
import com.insper.mini_spotify.usuario.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.HashMap;

@Service
public class JamService {

    private final UsuarioService usuarioService;
    private HashMap<Long, Jam> jams = new HashMap<>();

    public JamService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    public Jam cadastrarJam(Jam jam) {

        if (jam.getId() == null || jam.getUsuarios() == null || jam.getDataCriacao() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Jam não pode ser nulo");
        }

        for(Usuario usuario : jam.getUsuarios()) {
            usuarioService.getUsuario(usuario.getId());
        }

        jams.put(jam.getId(), jam);
        return jam;

    }

    public Collection<Jam> listarJams() {return jams.values();}

    public Jam getJam(Long id) {
        Jam jam = jams.get(id);
        if (jam == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Jam não encontrada");
        }
        return jam;
    }

    public Jam updateJam(Long id, Jam jam) {
        Jam jamAntigo = jams.get(id);
        if (jamAntigo == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Jam não encontrada");
        }
        if (jam.getUsuarios() != null) {
            for(Usuario usuario : jam.getUsuarios()) {
                usuarioService.getUsuario(usuario.getId());
            }
            jamAntigo.setUsuarios(jam.getUsuarios());
        }
        if (jam.getDataCriacao() != null) {
            jamAntigo.setDataCriacao(jam.getDataCriacao());
        }
        return jamAntigo;
    }

    public void deleteJam(Long id) {
        Jam jam = getJam(id);
        jams.remove(id);
    }

}