package com.insper.mini_spotify.artista.dto;

import com.insper.mini_spotify.artista.Artista;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ResponseArtistaDTO {
    private Integer id;
    private String nome;
    private String generoMusical;
    private String paisOrigem;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAtualizacao;

    public static @NonNull ResponseArtistaDTO toDTO(Artista artista) {
        ResponseArtistaDTO responseArtistaDTO = new ResponseArtistaDTO();
        responseArtistaDTO.setId(artista.getId());
        responseArtistaDTO.setNome(artista.getNome());
        responseArtistaDTO.setGeneroMusical(artista.getGeneroMusical());
        responseArtistaDTO.setPaisOrigem(artista.getPaisOrigem());
        responseArtistaDTO.setDataCriacao(artista.getDataCriacao());
        responseArtistaDTO.setDataAtualizacao(artista.getDataAtualizacao());
        return responseArtistaDTO;
    }


}
