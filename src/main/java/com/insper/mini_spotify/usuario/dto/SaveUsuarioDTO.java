package com.insper.mini_spotify.usuario.dto;

import com.insper.mini_spotify.usuario.TipoPlano;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveUsuarioDTO {

    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Formato de email inválido")
    private String email;

    @NotNull(message = "Tipo de plano é obrigatório")
    private TipoPlano tipoPlano;

    @NotNull(message = "O status ativo é obrigatório")
    private Boolean ativo;
}