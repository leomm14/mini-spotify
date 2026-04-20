package com.insper.mini_spotify.musica;

import com.insper.mini_spotify.musica.dto.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MusicaController {

    @Autowired
    private MusicaService musicaService;

    @GetMapping("/musicas")
    public Page<ResponseMusicaDTO> listMusicas(
            @RequestParam(required = false) String titulo,
            Pageable pageable) {
        return musicaService.list(titulo, pageable);
    }

    @GetMapping("/musicas/{id}")
    public ResponseMusicaDTO getMusica(@PathVariable Integer id) {
        return musicaService.getDTO(id);
    }

    @PostMapping("/musicas")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseMusicaDTO saveMusica(@Valid @RequestBody SaveMusicaDTO musica) {
        return musicaService.save(musica);
    }

    @PutMapping("/musicas/{id}")
    public ResponseMusicaDTO updateMusica(@PathVariable Integer id, @RequestBody EditMusicaDTO musica) {
        return musicaService.edit(id, musica);
    }

    @DeleteMapping("/musicas/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMusica(@PathVariable Integer id) {
        musicaService.delete(id);
    }

    @PostMapping("/musicas/{id}/reproduzir")
    public ResponseMusicaDTO reproduzMusica(
            @PathVariable Integer id,
            @RequestHeader("X-USER-ID") Integer idUsuario) {
        return musicaService.reproduzir(id, idUsuario);
    }

    @GetMapping("/relatorios/top-musicas")
    public List<TopMusicasDTO> geraTop10() {
        return musicaService.gerarTop10();
    }
}