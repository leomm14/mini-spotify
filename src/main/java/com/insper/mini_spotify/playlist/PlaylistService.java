package com.insper.mini_spotify.playlist;

import com.insper.mini_spotify.album.Album;
import com.insper.mini_spotify.artista.Artista;
import com.insper.mini_spotify.musica.Musica;
import com.insper.mini_spotify.musica.MusicaService;
import com.insper.mini_spotify.playlist.Playlist;
import com.insper.mini_spotify.usuario.Usuario;
import com.insper.mini_spotify.usuario.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import  java.util.HashMap;

@Service
public class PlaylistService {

    private final UsuarioService usuarioService;
    private final MusicaService musicaService;
    private HashMap<Long, Playlist> playlists = new HashMap<>();

    public PlaylistService(UsuarioService usuarioService, MusicaService musicaService) {
        this.usuarioService = usuarioService;
        this.musicaService = musicaService;
    }

    public Playlist cadastrarPlaylist(Playlist playlist) {

        if (playlist.getId() == null || playlist.getNome() == null || playlist.getPublica() == null
                || playlist.getDataCriacao() == null || playlist.getUsuario() == null || playlist.getMusicas() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Playlist não pode ser nulo");
        }

        Usuario usuario = playlist.getUsuario();
        usuarioService.getUsuario(usuario.getId());
        for(Musica musica : playlist.getMusicas()) {
            musicaService.getMusica(musica.getId());
        }

        playlists.put(playlist.getId(), playlist);
        return playlist;

    }

    public Collection<Playlist> listarPlaylists() {
        return playlists.values();
    }

    public Playlist getPlaylist(Long id) {
        Playlist playlist = playlists.get(id);
        if (playlist == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Playlist não encontrada");
        }
        return playlist;
    }

    public Playlist updatePlaylist(Long id, Playlist playlist) {
        Playlist playlistAntigo = playlists.get(id);
        if (playlistAntigo == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Música não encontrada");
        }
        if (playlist.getNome() != null) {
            playlistAntigo.setNome(playlist.getNome());
        }
        if (playlist.getPublica() != null) {
            playlistAntigo.setPublica(playlist.getPublica());
        }
        if (playlist.getDataCriacao() != null) {
            playlistAntigo.setDataCriacao(playlist.getDataCriacao());
        }
        if (playlist.getUsuario() != null) {
            Usuario usuario = playlist.getUsuario();
            usuarioService.getUsuario(usuario.getId());
            playlistAntigo.setUsuario(usuario);
        }
        if (playlist.getMusicas() != null) {
            for(Musica musica : playlist.getMusicas()) {
                musicaService.getMusica(musica.getId());
            }
            playlistAntigo.setMusicas(playlist.getMusicas());
        }
        return playlistAntigo;
    }

    public void deletePlaylist(Long id) {
        Playlist playlist = getPlaylist(id);
        playlists.remove(id);
    }

    public Playlist adicionaMusica(Long idPlaylist, Long idMusica, Long idUsuario) {
        Usuario usuario = usuarioService.getUsuario(idUsuario);
        Playlist playlist = getPlaylist(idPlaylist);
        Musica musica = musicaService.getMusica(idMusica);
        if (!playlist.getUsuario().getId().equals(usuario.getId()) ) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Apenas o dono da playlist pode adicionar músicas");
        }
        if (playlist.getMusicas().contains(musica)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"A música já está na playlist");
        }
        Collection<Musica> musicas = playlist.getMusicas();
        musicas.add(musica);
        playlist.setMusicas(musicas);
        return playlist;
    }
}