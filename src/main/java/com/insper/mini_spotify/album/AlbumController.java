package com.insper.mini_spotify.album;

import com.insper.mini_spotify.album.dto.EditAlbumDTO;
import com.insper.mini_spotify.album.dto.ResponseAlbumDTO;
import com.insper.mini_spotify.album.dto.SaveAlbumDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class AlbumController {

    @Autowired
    private AlbumService albumService;

    @GetMapping("/albuns")
    public Page<ResponseAlbumDTO> getAlbums(
            @RequestParam(required = false) String titulo,
            Pageable pageable) {
        return albumService.list(titulo, pageable);
    }

    @GetMapping("/albuns/{id}")
    public ResponseAlbumDTO getAlbum(@PathVariable Integer id) {
        return albumService.getDTO(id);
    }

    @PostMapping("/albuns")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseAlbumDTO saveAlbum(@Valid @RequestBody SaveAlbumDTO album) {
        return albumService.save(album);
    }

    @PutMapping("/albuns/{id}")
    public ResponseAlbumDTO updateAlbum(@PathVariable Integer id, @RequestBody EditAlbumDTO album) {
        return albumService.edit(id, album);
    }

    @DeleteMapping("/albuns/{id}")
    public void deleteAlbum(@PathVariable Integer id) {
        albumService.delete(id);
    }
}