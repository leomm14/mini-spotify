package com.insper.mini_spotify.musica.dto;

import com.insper.mini_spotify.album.dto.ResponseAlbumDTO;
import com.insper.mini_spotify.artista.dto.ResponseArtistaDTO;
import com.insper.mini_spotify.musica.Musica;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ResponseMusicaDTO {
    private Integer id;
    private String titulo;
    private Integer duracaoSegundos;
    private Integer numeroFaixa;
    private Long totalReproducoes;
    private ResponseArtistaDTO artista;
    private ResponseAlbumDTO album;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAtualizacao;

    public static ResponseMusicaDTO toDTO(Musica musica) {
        ResponseMusicaDTO dto = new ResponseMusicaDTO();
        dto.setId(musica.getId());
        dto.setTitulo(musica.getTitulo());
        dto.setDuracaoSegundos(musica.getDuracaoSegundos());
        dto.setNumeroFaixa(musica.getNumeroFaixa());
        dto.setTotalReproducoes(musica.getTotalReproducoes());
        dto.setDataCriacao(musica.getDataCriacao());
        dto.setDataAtualizacao(musica.getDataAtualizacao());
        if (musica.getArtista() != null) {
            dto.setArtista(ResponseArtistaDTO.toDTO(musica.getArtista()));
        }

        return dto;
    }
}