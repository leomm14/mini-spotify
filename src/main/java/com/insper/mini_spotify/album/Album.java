package com.insper.mini_spotify.album;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.insper.mini_spotify.album.dto.SaveAlbumDTO;
import com.insper.mini_spotify.artista.Artista;
import com.insper.mini_spotify.musica.Musica;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private LocalDate dataLancamento;

    @ManyToOne
    @JoinColumn(name = "id_artista", nullable = false)
    private Artista artista;

    @JsonIgnore
    @OneToMany(mappedBy = "album")
    private List<Musica> musicas;

    @CreationTimestamp
    private LocalDateTime dataCriacao;

    @UpdateTimestamp
    private LocalDateTime dataAtualizacao;

    public static Album toModel(SaveAlbumDTO dto, Artista artista) {
        Album album = new Album();
        album.setTitulo(dto.getTitulo());
        album.setDataLancamento(dto.getDataLancamento());
        album.setArtista(artista);
        return album;
    }
}