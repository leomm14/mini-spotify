package com.insper.mini_spotify.playlist;

import com.insper.mini_spotify.playlist.dto.EditPlaylistDTO;
import com.insper.mini_spotify.playlist.dto.ResponsePlaylistDTO;
import com.insper.mini_spotify.playlist.dto.SavePlaylistDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class PlaylistController {

    @Autowired
    private PlaylistService playlistService;

    @GetMapping("/playlists")
    public Page<ResponsePlaylistDTO> getPlaylists(
            @RequestParam(required = false) String nome,
            Pageable pageable) {
        return playlistService.list(nome, pageable);
    }

    @GetMapping("/playlists/{id}")
    public ResponsePlaylistDTO getPlaylist(@PathVariable Integer id) {
        return playlistService.getDTO(id);
    }

    @PostMapping("/playlists")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponsePlaylistDTO savePlaylist(@Valid @RequestBody SavePlaylistDTO playlist) {
        return playlistService.save(playlist);
    }

    @PutMapping("/playlists/{id}")
    public ResponsePlaylistDTO updatePlaylist(@PathVariable Integer id, @RequestBody EditPlaylistDTO playlist) {
        return playlistService.edit(id, playlist);
    }

    @DeleteMapping("/playlists/{id}")
    public void deletePlaylist(@PathVariable Integer id) {
        playlistService.delete(id);
    }

    @PostMapping("/playlists/{playlistId}/musicas/{musicaId}")
    public ResponsePlaylistDTO adicionaMusica(
            @PathVariable Integer playlistId,
            @PathVariable Integer musicaId,
            @RequestHeader("X-USER-ID") Integer idUsuario) {
        return playlistService.adicionaMusica(playlistId, musicaId, idUsuario);
    }
}