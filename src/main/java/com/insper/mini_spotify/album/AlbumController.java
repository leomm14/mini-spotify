package com.insper.mini_spotify.album;

import com.insper.mini_spotify.album.Album;
import com.insper.mini_spotify.album.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class AlbumController {

    @Autowired
    private AlbumService albumService;

    @GetMapping("/albuns")
    public Collection<Album> getAlbums() {return albumService.listarAlbums();}

    @GetMapping("/albuns/{id}")
    public Album getAlbum(@PathVariable Long id) {return albumService.getAlbum(id);}

    @PostMapping("/albuns")
    @ResponseStatus(HttpStatus.CREATED)
    public Album saveAlbum(@RequestBody Album album) {return albumService.cadastrarAlbum(album);}

    @PutMapping("/albuns/{id}")
    public Album updateAlbum(@PathVariable Long id, @RequestBody Album album) {return albumService.updateAlbum(id, album);}

    @DeleteMapping("/albuns/{id}")
    public void deleteAlbum(@PathVariable Long id) {albumService.deleteAlbum(id);}

}