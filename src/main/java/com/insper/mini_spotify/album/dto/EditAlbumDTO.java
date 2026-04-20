package com.insper.mini_spotify.album.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class EditAlbumDTO {
    private String titulo;
    private LocalDate dataLancamento;
}