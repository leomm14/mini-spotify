package com.insper.mini_spotify.playlist;

import com.insper.mini_spotify.musica.Musica;
import com.insper.mini_spotify.musica.MusicaService;
import com.insper.mini_spotify.playlist.dto.*;
import com.insper.mini_spotify.usuario.Usuario;
import com.insper.mini_spotify.usuario.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlaylistService {

    @Autowired
    private PlaylistRepository playlistRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private MusicaService musicaService;

    public ResponsePlaylistDTO save(SavePlaylistDTO dto) {
        Usuario usuario = usuarioService.get(dto.getIdUsuario());

        List<Musica> musicas = new ArrayList<>();
        for (Integer id : dto.getIdsMusicas()) {
            Musica musica = musicaService.get(id);
            musicas.add(musica);
        }

        Playlist playlist = Playlist.toModel(dto, usuario, musicas);
        playlist = playlistRepository.save(playlist);

        return ResponsePlaylistDTO.toDTO(playlist);
    }

    public Page<ResponsePlaylistDTO> list(String nome, Pageable pageable) {
        if (nome != null) {
            return playlistRepository.findByNomeContaining(nome, pageable)
                    .map(ResponsePlaylistDTO::toDTO);
        }
        return playlistRepository.findAll(pageable).map(ResponsePlaylistDTO::toDTO);
    }

    public Playlist get(Integer id) {
        return playlistRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Playlist não encontrada"));
    }

    public ResponsePlaylistDTO getDTO(Integer id) {
        return ResponsePlaylistDTO.toDTO(get(id));
    }

    public ResponsePlaylistDTO edit(Integer id, EditPlaylistDTO dto) {
        Playlist playlistDB = get(id);

        if (dto.getNome() != null) playlistDB.setNome(dto.getNome());
        if (dto.getPublica() != null) playlistDB.setPublica(dto.getPublica());

        playlistDB = playlistRepository.save(playlistDB);
        return ResponsePlaylistDTO.toDTO(playlistDB);
    }

    public void delete(Integer id) {
        Playlist playlist = get(id);
        playlistRepository.delete(playlist);
    }

    public ResponsePlaylistDTO adicionaMusica(Integer idPlaylist, Integer idMusica, Integer idUsuario) {
        Usuario usuario = usuarioService.get(idUsuario);
        Playlist playlist = get(idPlaylist);
        Musica musica = musicaService.get(idMusica);

        if (!playlist.getUsuario().getId().equals(usuario.getId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Apenas o dono da playlist pode adicionar músicas");
        }

        if (playlist.getMusicas().contains(musica)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A música já está na playlist");
        }

        playlist.getMusicas().add(musica);
        playlist = playlistRepository.save(playlist);

        return ResponsePlaylistDTO.toDTO(playlist);
    }
}