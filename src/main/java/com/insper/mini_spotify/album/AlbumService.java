package com.insper.mini_spotify.album;

import com.insper.mini_spotify.album.Album;
import com.insper.mini_spotify.artista.Artista;
import com.insper.mini_spotify.artista.ArtistaService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import  java.util.HashMap;

@Service
public class AlbumService {

    private final ArtistaService artistaService;
    private HashMap<Long, Album> albuns = new HashMap<>();

    public AlbumService(ArtistaService artistaService) {
        this.artistaService = artistaService;
    }

    public Album cadastrarAlbum(Album album) {

        if (album.getId() == null || album.getTitulo() == null || album.getDataLancamento() == null || album.getArtista() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Álbum não pode ser nulo");
        }

        Artista artista = album.getArtista();
        artistaService.getArtista(artista.getId());

        albuns.put(album.getId(), album);
        return album;

    }

    public Collection<Album> listarAlbums() {return albuns.values();}

    public Album getAlbum(Long id) {
        Album album = albuns.get(id);
        if (album == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Álbum não encontrado");
        }
        return album;
    }

    public Album updateAlbum(Long id, Album album) {
        Album albumAntigo = albuns.get(id);
        if (albumAntigo == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Álbum não encontrado");
        }
        if (album.getTitulo() != null) {
            albumAntigo.setTitulo(album.getTitulo());
        }
        if (album.getDataLancamento() != null) {
            albumAntigo.setDataLancamento(album.getDataLancamento());
        }
        if (album.getArtista() != null) {
            Artista artista = album.getArtista();
            artistaService.getArtista(artista.getId());
            albumAntigo.setArtista(artista);
        }
        return albumAntigo;
    }

    public void deleteAlbum(Long id) {
        Album album = getAlbum(id);
        albuns.remove(id);
    }

}