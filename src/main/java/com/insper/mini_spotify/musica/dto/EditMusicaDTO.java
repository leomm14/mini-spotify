package com.insper.mini_spotify.musica.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EditMusicaDTO {
    private String titulo;
    private Integer duracaoSegundos;
    private Integer numeroFaixa;
    private Long totalReproducoes;
}