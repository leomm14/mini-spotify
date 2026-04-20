package com.insper.mini_spotify.musica.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TopMusicasDTO {
    private String titulo;
    private String nomeArtista;
    private Long totalReproducoes;
}