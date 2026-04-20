package com.insper.mini_spotify.playlist;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.insper.mini_spotify.musica.Musica;
import com.insper.mini_spotify.usuario.Usuario;
import com.insper.mini_spotify.playlist.dto.SavePlaylistDTO;
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
public class Playlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private Boolean publica;


    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @ManyToMany
    @JoinTable(
            name = "playlist_musica",
            joinColumns = @JoinColumn(name = "playlist_id"),
            inverseJoinColumns = @JoinColumn(name = "musica_id")
    )
    private List<Musica> musicas;


    @CreationTimestamp
    private LocalDateTime dataCriacao;

    @UpdateTimestamp
    private LocalDateTime dataAtualizacao;

    public static Playlist toModel(SavePlaylistDTO dto, Usuario usuario, List<Musica> musicas) {
        Playlist playlist = new Playlist();
        playlist.setNome(dto.getNome());
        playlist.setPublica(dto.getPublica());
        playlist.setUsuario(usuario);
        playlist.setMusicas(musicas);
        return playlist;
    }
}