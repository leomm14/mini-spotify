package com.insper.mini_spotify.musica.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveMusicaDTO {

    @NotBlank(message = "Título é obrigatório")
    private String titulo;

    @NotNull(message = "Duração é obrigatória")
    @Min(value = 1, message = "A duração deve ser maior que zero")
    private Integer duracaoSegundos;

    private Integer numeroFaixa;

    private Long totalReproducoes;

    @NotNull(message = "O ID do artista é obrigatório")
    private Integer idArtista;

    private Integer idAlbum;
}