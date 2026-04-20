package com.insper.mini_spotify.usuario.dto;

import com.insper.mini_spotify.usuario.TipoPlano;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EditUsuarioDTO {
    private String nome;
    private TipoPlano tipoPlano;
    private Boolean ativo;
}