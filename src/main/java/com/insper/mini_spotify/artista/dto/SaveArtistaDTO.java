package com.insper.mini_spotify.artista.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveArtistaDTO {
    @NotBlank(message = "Nome é obrigatório")
    private String nome;
    @NotBlank(message = "Gênero musical é obrigatório")
    private String generoMusical;
    @NotBlank(message = "País de origem é obrigatório")
    private String paisOrigem;
}
