package com.insper.mini_spotify.jam.dto;

import com.insper.mini_spotify.jam.Jam;
import com.insper.mini_spotify.usuario.dto.ResponseUsuarioDTO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class ResponseJamDTO {
    private Integer id;
    private List<ResponseUsuarioDTO> usuarios;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAtualizacao;

    public static ResponseJamDTO toDTO(Jam jam) {
        ResponseJamDTO dto = new ResponseJamDTO();
        dto.setId(jam.getId());
        dto.setDataCriacao(jam.getDataCriacao());
        dto.setDataAtualizacao(jam.getDataAtualizacao());

        if (jam.getUsuarios() != null) {
            dto.setUsuarios(jam.getUsuarios().stream()
                    .map(ResponseUsuarioDTO::toDTO)
                    .collect(Collectors.toList()));
        }

        return dto;
    }
}