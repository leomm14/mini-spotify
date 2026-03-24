package com.insper.mini_spotify.musica;

import com.insper.mini_spotify.album.Album;
import com.insper.mini_spotify.album.AlbumService;
import com.insper.mini_spotify.artista.Artista;
import com.insper.mini_spotify.artista.ArtistaService;
import com.insper.mini_spotify.musica.Musica;
import com.insper.mini_spotify.usuario.Usuario;
import com.insper.mini_spotify.usuario.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
public class MusicaService {

    private final UsuarioService usuarioService;
    private final ArtistaService artistaService;
    private final AlbumService albumService;
    private HashMap<Long, Musica> musicas = new HashMap<>();

    public MusicaService(UsuarioService usuarioService, ArtistaService artistaService, AlbumService albumService) {
        this.usuarioService = usuarioService;
        this.artistaService = artistaService;
        this.albumService = albumService;
    }

    public Musica cadastrarMusica(Musica musica) {

        if (musica.getId() == null || musica.getTitulo() == null || musica.getDuracaoSegundos() == null
                || musica.getNumeroFaixa() == null || musica.getAlbum() == null || musica.getArtista() == null || musica.getTotalReproducoes() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Música não pode ser nulo");
        }

        Album album = musica.getAlbum();
        albumService.getAlbum(album.getId());
        Artista artista = musica.getArtista();
        artistaService.getArtista(artista.getId());

        musicas.put(musica.getId(), musica);
        return musica;

    }

    public Collection<Musica> listarMusicas() {return musicas.values();}

    public Musica getMusica(Long id) {
        Musica musica = musicas.get(id);
        if (musica == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Música não encontrada");
        }
        return musica;
    }

    public Musica updateMusica(Long id, Musica musica) {
        Musica musicaAntigo = musicas.get(id);
        if (musicaAntigo == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Música não encontrada");
        }
        if (musica.getTitulo() != null) {
            musicaAntigo.setTitulo(musica.getTitulo());
        }
        if (musica.getDuracaoSegundos() != null) {
            musicaAntigo.setDuracaoSegundos(musica.getDuracaoSegundos());
        }
        if (musica.getNumeroFaixa() != null) {
            musicaAntigo.setNumeroFaixa(musica.getNumeroFaixa());
        }
        if (musica.getAlbum() != null) {
            Album album = musica.getAlbum();
            albumService.getAlbum(album.getId());
            musicaAntigo.setAlbum(album);
        }
        if (musica.getArtista() != null) {
            Artista artista = musica.getArtista();
            artistaService.getArtista(artista.getId());
            musicaAntigo.setArtista(artista);
        }
        if (musica.getTotalReproducoes() != null) {
            musicaAntigo.setTotalReproducoes(musica.getTotalReproducoes());
        }
        return musicaAntigo;
    }

    public void deleteMusica(Long id) {
        Musica musica = getMusica(id);
        musicas.remove(id);
    }

    public Musica reproduzMusica(Long idMusica, Long idUsuario) {
        Usuario usuario = usuarioService.getUsuario(idUsuario);
        if (!usuario.getAtivo()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário está inativo");
        }
        Musica musica = getMusica(idMusica);
        musica.setTotalReproducoes(musica.getTotalReproducoes() + 1);
        return musica;
    }

    public List<TopMusicasDTO> geratop10() {
        List<TopMusicasDTO> topMusicas = new ArrayList<>();

        List<Musica> top10 = musicas.values()
                .stream()
                .sorted(Comparator.comparing(Musica::getTotalReproducoes).reversed())
                .limit(10)
                .toList();

        for (Musica musica : top10) {
            TopMusicasDTO topMusicasDTO = new TopMusicasDTO(musica.getTitulo(), musica.getArtista().getNome(), musica.getTotalReproducoes());
            topMusicas.add(topMusicasDTO);
        }

        return topMusicas;



    }

}