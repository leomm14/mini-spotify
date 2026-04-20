package com.insper.mini_spotify.jam.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SaveJamDTO {

    @NotEmpty(message = "A Jam precisa de pelo menos um usuário")
    private List<Integer> idsUsuarios;
}