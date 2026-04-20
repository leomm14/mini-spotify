package com.insper.mini_spotify.usuario;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.insper.mini_spotify.jam.Jam;
import com.insper.mini_spotify.playlist.Playlist;
import com.insper.mini_spotify.usuario.dto.SaveUsuarioDTO;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    private TipoPlano tipoPlano;

    private Boolean ativo;

    @JsonIgnore
    @OneToMany(mappedBy = "usuario")
    private List<Playlist> playlists;

    @JsonIgnore
    @ManyToMany(mappedBy = "usuarios")
    private List<Jam> jams;

    @CreationTimestamp
    private LocalDateTime dataCriacao;

    @UpdateTimestamp
    private LocalDateTime dataAtualizacao;

    public static Usuario toModel(SaveUsuarioDTO saveUsuarioDTO) {
        Usuario usuario = new Usuario();
        usuario.setNome(saveUsuarioDTO.getNome());
        usuario.setEmail(saveUsuarioDTO.getEmail());
        usuario.setTipoPlano(saveUsuarioDTO.getTipoPlano());
        usuario.setAtivo(saveUsuarioDTO.getAtivo());
        return usuario;
    }
}