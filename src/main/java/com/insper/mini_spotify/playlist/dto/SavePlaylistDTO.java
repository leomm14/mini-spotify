package com.insper.mini_spotify.playlist.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SavePlaylistDTO {

    @NotBlank(message = "O nome da playlist é obrigatório")
    private String nome;

    @NotNull(message = "O campo pública é obrigatório")
    private Boolean publica;

    @NotNull(message = "O ID do usuário dono é obrigatório")
    private Integer idUsuario;

    @NotNull(message = "A playlist precisa de pelo menos uma música")
    private List<Integer> idsMusicas;
}