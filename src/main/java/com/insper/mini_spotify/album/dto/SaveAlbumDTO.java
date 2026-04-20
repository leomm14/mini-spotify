package com.insper.mini_spotify.album.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class SaveAlbumDTO {

    @NotBlank(message = "O título do álbum é obrigatório")
    private String titulo;

    @NotNull(message = "A data de lançamento é obrigatória")
    private LocalDate dataLancamento;

    @NotNull(message = "O ID do artista é obrigatório")
    private Integer idArtista;
}