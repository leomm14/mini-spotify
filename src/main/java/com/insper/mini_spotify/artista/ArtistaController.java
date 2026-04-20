package com.insper.mini_spotify.artista;

import com.insper.mini_spotify.artista.Artista;
import com.insper.mini_spotify.artista.ArtistaService;
import com.insper.mini_spotify.artista.dto.EditArtistaDTO;
import com.insper.mini_spotify.artista.dto.ResponseArtistaDTO;
import com.insper.mini_spotify.artista.dto.SaveArtistaDTO;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/artistas")
public class ArtistaController {

    @Autowired
    private ArtistaService artistaService;

    @GetMapping
    public Page<ResponseArtistaDTO> listArtistas(
            @RequestParam(required = false, name = "nome") String nome,
            Pageable pageable) {
        return artistaService.list(nome, pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseArtistaDTO saveArtista(@Valid @RequestBody SaveArtistaDTO artista) {return artistaService.save(artista);}

    @GetMapping("/{id}")
    public ResponseArtistaDTO getArtista(@PathVariable Integer id) {return  artistaService.getDTO(id);}

    @PutMapping("/{id}")
    public ResponseArtistaDTO editArtista(@PathVariable Integer id, @RequestBody EditArtistaDTO artista) {
        return artistaService.edit(id, artista);
    }

    @DeleteMapping("/{id}")
    public void deleteArtista(@PathVariable Integer id) {artistaService.delete(id);}

}
