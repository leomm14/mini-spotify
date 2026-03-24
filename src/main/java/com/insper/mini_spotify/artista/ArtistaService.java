package com.insper.mini_spotify.artista;

import com.insper.mini_spotify.artista.Artista;
import com.insper.mini_spotify.usuario.Usuario;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import  java.util.HashMap;

@Service
public class ArtistaService {

    private HashMap<Long, Artista> artistas = new HashMap<>();

    public Artista cadastrarArtista(Artista artista) {

        if (artista.getId() == null || artista.getNome() == null || artista.getGeneroMusical() == null || artista.getPaisOrigem() == null ) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Artista não pode ser nulo");
        }
        artistas.put(artista.getId(), artista);
        return artista;

    }

    public Collection<Artista> listarArtistas() {return artistas.values();}

    public Artista getArtista(Long id) {
        Artista artista = artistas.get(id);
        if (artista == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Artista não encontrado");
        }
        return artista;
    }

    public Artista updateArtista(Long id, Artista artista) {
        Artista artistaAntigo = artistas.get(id);
        if (artistaAntigo == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Artista não encontrado");
        }
        if (artista.getNome() != null) {
            artistaAntigo.setNome(artista.getNome());
        }
        if (artista.getGeneroMusical() != null) {
            artistaAntigo.setGeneroMusical(artista.getGeneroMusical());
        }
        if (artista.getPaisOrigem() != null) {
            artistaAntigo.setPaisOrigem(artista.getPaisOrigem());
        }
        return artistaAntigo;
    }

    public void deleteArtista(Long id) {
        Artista artista = getArtista(id);
        artistas.remove(id);
    }

}
