package com.insper.mini_spotify.artista;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.insper.mini_spotify.album.Album;
import com.insper.mini_spotify.musica.Musica;
import com.insper.mini_spotify.artista.dto.SaveArtistaDTO;
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
public class Artista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;
    private String generoMusical;
    private String paisOrigem;

    @JsonIgnore
    @OneToMany(mappedBy = "artista")
    private List<Album> albuns;

    @JsonIgnore
    @OneToMany(mappedBy = "artista")
    private List<Musica> musicas;

    @CreationTimestamp
    private LocalDateTime dataCriacao;

    @UpdateTimestamp
    private LocalDateTime dataAtualizacao;

    public static Artista toModel(SaveArtistaDTO saveArtistaDTO) {
        Artista artista = new Artista();
        artista.setNome(saveArtistaDTO.getNome());
        artista.setGeneroMusical(saveArtistaDTO.getGeneroMusical());
        artista.setPaisOrigem(saveArtistaDTO.getPaisOrigem());
        return artista;
    }
}