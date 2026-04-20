package com.insper.mini_spotify.playlist.dto;

import com.insper.mini_spotify.playlist.Playlist;
import com.insper.mini_spotify.usuario.dto.ResponseUsuarioDTO;
import com.insper.mini_spotify.musica.dto.ResponseMusicaDTO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class ResponsePlaylistDTO {
    private Integer id;
    private String nome;
    private Boolean publica;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAtualizacao;
    private ResponseUsuarioDTO usuario;
    private List<ResponseMusicaDTO> musicas;

    public static ResponsePlaylistDTO toDTO(Playlist playlist) {
        ResponsePlaylistDTO dto = new ResponsePlaylistDTO();
        dto.setId(playlist.getId());
        dto.setNome(playlist.getNome());
        dto.setPublica(playlist.getPublica());
        dto.setDataCriacao(playlist.getDataCriacao());
        dto.setDataAtualizacao(playlist.getDataAtualizacao());

        if (playlist.getUsuario() != null) {
            dto.setUsuario(ResponseUsuarioDTO.toDTO(playlist.getUsuario()));
        }

        if (playlist.getMusicas() != null) {
            dto.setMusicas(playlist.getMusicas().stream()
                    .map(ResponseMusicaDTO::toDTO)
                    .collect(Collectors.toList()));
        }

        return dto;
    }
}