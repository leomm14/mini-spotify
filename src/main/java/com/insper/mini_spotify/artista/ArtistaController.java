package com.insper.mini_spotify.artista;

import com.insper.mini_spotify.artista.Artista;
import com.insper.mini_spotify.artista.ArtistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class ArtistaController {

    @Autowired
    private ArtistaService artistaService;

    @GetMapping("/artistas")
    public Collection<Artista> getArtistas() {return artistaService.listarArtistas();}

    @GetMapping("/artistas/{id}")
    public Artista getArtista(@PathVariable Long id) {return artistaService.getArtista(id);}

    @PostMapping("/artistas")
    @ResponseStatus(HttpStatus.CREATED)
    public Artista saveArtista(@RequestBody Artista artista) {return artistaService.cadastrarArtista(artista);}

    @PutMapping("/artistas/{id}")
    public Artista updateArtista(@PathVariable Long id, @RequestBody Artista artista) {return artistaService.updateArtista(id, artista);}

    @DeleteMapping("/artistas/{id}")
    public void deleteArtista(@PathVariable Long id) {artistaService.deleteArtista(id);}

}
