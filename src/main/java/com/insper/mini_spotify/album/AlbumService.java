package com.insper.mini_spotify.album;

import com.insper.mini_spotify.album.dto.EditAlbumDTO;
import com.insper.mini_spotify.album.dto.ResponseAlbumDTO;
import com.insper.mini_spotify.album.dto.SaveAlbumDTO;
import com.insper.mini_spotify.artista.Artista;
import com.insper.mini_spotify.artista.ArtistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AlbumService {

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private ArtistaService artistaService;

    public ResponseAlbumDTO save(SaveAlbumDTO dto) {
        Artista artista = artistaService.get(dto.getIdArtista());

        Album album = Album.toModel(dto, artista);
        album = albumRepository.save(album);
        return ResponseAlbumDTO.toDTO(album);
    }

    public Page<ResponseAlbumDTO> list(String titulo, Pageable pageable) {
        if (titulo != null) {
            return albumRepository
                    .findByTituloContaining(titulo, pageable)
                    .map(album -> ResponseAlbumDTO.toDTO(album));
        }
        return albumRepository
                .findAll(pageable)
                .map(album -> ResponseAlbumDTO.toDTO(album));
    }

    public Album get(Integer id) {
        return albumRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Álbum não encontrado"));
    }

    public ResponseAlbumDTO getDTO(Integer id) {
        return ResponseAlbumDTO.toDTO(get(id));
    }

    public ResponseAlbumDTO edit(Integer id, EditAlbumDTO dto) {
        Album albumDB = get(id);

        if (dto.getTitulo() != null) {
            albumDB.setTitulo(dto.getTitulo());
        }
        if (dto.getDataLancamento() != null) {
            albumDB.setDataLancamento(dto.getDataLancamento());
        }

        albumDB = albumRepository.save(albumDB);
        return ResponseAlbumDTO.toDTO(albumDB);
    }

    public void delete(Integer id) {
        Album album = get(id);
        albumRepository.delete(album);
    }
}