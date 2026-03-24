package com.insper.mini_spotify.musica;

import com.insper.mini_spotify.musica.Musica;
import com.insper.mini_spotify.musica.MusicaService;
import com.insper.mini_spotify.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
public class MusicaController {

    @Autowired
    private MusicaService musicaService;

    @GetMapping("/musicas")
    public Collection<Musica> getMusicas() {return musicaService.listarMusicas();}

    @GetMapping("/musicas/{id}")
    public Musica getMusica(@PathVariable Long id) {return musicaService.getMusica(id);}

    @PostMapping("/musicas")
    @ResponseStatus(HttpStatus.CREATED)
    public Musica saveMusica(@RequestBody Musica musica) {return musicaService.cadastrarMusica(musica);}

    @PutMapping("/musicas/{id}")
    public Musica updateMusica(@PathVariable Long id, @RequestBody Musica musica) {return musicaService.updateMusica(id, musica);}

    @DeleteMapping("/musicas/{id}")
    public void deleteMusica(@PathVariable Long id) {musicaService.deleteMusica(id);}

    @PostMapping("/musicas/{id}/reproduzir")
    public Musica reproduzMusica(@PathVariable Long id, @RequestHeader("X-USER-ID") Long idUsuario) {return musicaService.reproduzMusica(id, idUsuario);}

    @GetMapping("/relatorios/top-musicas")
    public List<TopMusicasDTO> geraTop10() {return musicaService.geratop10();}

}