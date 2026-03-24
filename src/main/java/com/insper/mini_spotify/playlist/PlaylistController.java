package com.insper.mini_spotify.playlist;

import com.insper.mini_spotify.playlist.Playlist;
import com.insper.mini_spotify.playlist.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class PlaylistController {

    @Autowired
    private PlaylistService playlistService;

    @GetMapping("/playlists")
    public Collection<Playlist> getPlaylists() {return playlistService.listarPlaylists();}

    @GetMapping("/playlists/{id}")
    public Playlist getPlaylist(@PathVariable Long id) {return playlistService.getPlaylist(id);}

    @PostMapping("/playlists")
    @ResponseStatus(HttpStatus.CREATED)
    public Playlist savePlaylist(@RequestBody Playlist playlist) {return playlistService.cadastrarPlaylist(playlist);}

    @PutMapping("/playlists/{id}")
    public Playlist updatePlaylist(@PathVariable Long id, @RequestBody Playlist playlist) {return playlistService.updatePlaylist(id, playlist);}

    @DeleteMapping("/playlists/{id}")
    public void deletePlaylist(@PathVariable Long id) {playlistService.deletePlaylist(id);}

    @PostMapping("/playlists/{playlistId}/musicas/{musicaId}")
    public Playlist adicionaMusica(@PathVariable("playlistId") Long idPlaylist, @PathVariable("musicaId") Long idMusica, @RequestHeader("X-USER-ID") Long idUsuario) {return playlistService.adicionaMusica(idPlaylist, idMusica,idUsuario);}

}
