package com.insper.mini_spotify.usuario.dto;

import com.insper.mini_spotify.usuario.TipoPlano;
import com.insper.mini_spotify.usuario.Usuario;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ResponseUsuarioDTO {
    private Integer id;
    private String nome;
    private String email;
    private TipoPlano tipoPlano;
    private Boolean ativo;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAtualizacao;

    public static @NonNull ResponseUsuarioDTO toDTO(Usuario usuario) {
        ResponseUsuarioDTO responseUsuarioDTO = new ResponseUsuarioDTO();
        responseUsuarioDTO.setId(usuario.getId());
        responseUsuarioDTO.setNome(usuario.getNome());
        responseUsuarioDTO.setEmail(usuario.getEmail());
        responseUsuarioDTO.setTipoPlano(usuario.getTipoPlano());
        responseUsuarioDTO.setAtivo(usuario.getAtivo());
        responseUsuarioDTO.setDataCriacao(usuario.getDataCriacao());
        responseUsuarioDTO.setDataAtualizacao(usuario.getDataAtualizacao());
        return responseUsuarioDTO;
    }
}