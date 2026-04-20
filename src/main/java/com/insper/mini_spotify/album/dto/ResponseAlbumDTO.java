package com.insper.mini_spotify.album.dto;

import com.insper.mini_spotify.album.Album;
import com.insper.mini_spotify.artista.dto.ResponseArtistaDTO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class ResponseAlbumDTO {
    private Integer id;
    private String titulo;
    private LocalDate dataLancamento;
    private ResponseArtistaDTO artista;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAtualizacao;

    public static ResponseAlbumDTO toDTO(Album album) {
        ResponseAlbumDTO dto = new ResponseAlbumDTO();
        dto.setId(album.getId());
        dto.setTitulo(album.getTitulo());
        dto.setDataLancamento(album.getDataLancamento());
        dto.setDataCriacao(album.getDataCriacao());
        dto.setDataAtualizacao(album.getDataAtualizacao());

        if (album.getArtista() != null) {
            dto.setArtista(ResponseArtistaDTO.toDTO(album.getArtista()));
        }

        return dto;
    }
}