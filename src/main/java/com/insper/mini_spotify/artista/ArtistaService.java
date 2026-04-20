package com.insper.mini_spotify.artista;

import com.insper.mini_spotify.artista.Artista;
import com.insper.mini_spotify.artista.dto.EditArtistaDTO;
import com.insper.mini_spotify.artista.dto.ResponseArtistaDTO;
import com.insper.mini_spotify.artista.dto.SaveArtistaDTO;
import com.insper.mini_spotify.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import  java.util.HashMap;

@Service
public class ArtistaService {

    @Autowired
    private ArtistaRepository artistaRepository;

    public ResponseArtistaDTO save(SaveArtistaDTO saveArtistaDTO) {
        Artista artista = Artista.toModel(saveArtistaDTO);
        artista = artistaRepository.save(artista);
        return ResponseArtistaDTO.toDTO(artista);

    }

    public Page<ResponseArtistaDTO> list(String nome, Pageable pageable) {
        if (nome != null) {
            return artistaRepository
                    .findByNomeContaining(nome, pageable)
                    .map(artista -> ResponseArtistaDTO.toDTO(artista));
        }
        return artistaRepository
                .findAll(pageable)
                .map(artista -> ResponseArtistaDTO.toDTO(artista));
    }

    public Artista get(Integer id) {
        return artistaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Artista não encontrado"));
    }

    public ResponseArtistaDTO getDTO(Integer id) {return ResponseArtistaDTO.toDTO(get(id));}


    public ResponseArtistaDTO edit(Integer id, EditArtistaDTO editArtistaDTO) {
        Artista artistaDB = get(id);

        if (editArtistaDTO.getNome() != null) {
            artistaDB.setNome(editArtistaDTO.getNome());
        }
        if (editArtistaDTO.getGeneroMusical() != null) {
            artistaDB.setGeneroMusical(editArtistaDTO.getGeneroMusical());
        }
        if (editArtistaDTO.getPaisOrigem() != null) {
            artistaDB.setPaisOrigem(editArtistaDTO.getPaisOrigem());
        }

        artistaDB = artistaRepository.save(artistaDB);
        return ResponseArtistaDTO.toDTO(artistaDB);
    }

    public void delete(Integer id) {
        Artista artista = get(id);
        artistaRepository.delete(artista);
    }

}
