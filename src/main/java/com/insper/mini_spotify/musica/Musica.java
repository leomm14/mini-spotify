package com.insper.mini_spotify.musica;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.insper.mini_spotify.album.Album;
import com.insper.mini_spotify.artista.Artista;
import com.insper.mini_spotify.playlist.Playlist;
import com.insper.mini_spotify.musica.dto.SaveMusicaDTO;
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
public class Musica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String titulo;
    private Integer duracaoSegundos;
    private Integer numeroFaixa;
    private Long totalReproducoes;

    @ManyToOne
    @JoinColumn(name = "id_album")
    private Album album;

    @ManyToOne
    @JoinColumn(name = "id_artista")
    private Artista artista;

    @JsonIgnore
    @ManyToMany(mappedBy = "musicas")
    private List<Playlist> playlists;

    @CreationTimestamp
    private LocalDateTime dataCriacao;

    @UpdateTimestamp
    private LocalDateTime dataAtualizacao;

    public static Musica toModel(SaveMusicaDTO dto, Artista artista, Album album) {
        Musica musica = new Musica();
        musica.setTitulo(dto.getTitulo());
        musica.setDuracaoSegundos(dto.getDuracaoSegundos());
        musica.setNumeroFaixa(dto.getNumeroFaixa());
        musica.setTotalReproducoes(dto.getTotalReproducoes());
        musica.setArtista(artista);
        musica.setAlbum(album);
        return musica;
    }
}