package com.insper.mini_spotify.musica;

import com.insper.mini_spotify.album.Album;
import com.insper.mini_spotify.album.AlbumService;
import com.insper.mini_spotify.artista.Artista;
import com.insper.mini_spotify.artista.ArtistaService;
import com.insper.mini_spotify.musica.dto.*;
import com.insper.mini_spotify.usuario.Usuario;
import com.insper.mini_spotify.usuario.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MusicaService {

    @Autowired
    private MusicaRepository musicaRepository;

    @Autowired
    private ArtistaService artistaService;

    @Autowired
    private AlbumService albumService;

    @Autowired
    private UsuarioService usuarioService;

    public ResponseMusicaDTO save(SaveMusicaDTO dto) {
        Artista artista = artistaService.get(dto.getIdArtista());
        Album album = albumService.get(dto.getIdAlbum());

        Musica musica = Musica.toModel(dto, artista, album);
        musica = musicaRepository.save(musica);
        return ResponseMusicaDTO.toDTO(musica);
    }

    public Page<ResponseMusicaDTO> list(String titulo, Pageable pageable) {
        if (titulo != null) {
            return musicaRepository
                    .findByTituloContaining(titulo, pageable)
                    .map(Musica -> ResponseMusicaDTO.toDTO(Musica));
        }
        return musicaRepository.findAll(pageable).map(Musica -> ResponseMusicaDTO.toDTO(Musica));
    }

    public Musica get(Integer id) {
        return musicaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Música não encontrada"));
    }

    public ResponseMusicaDTO getDTO(Integer id) {
        return ResponseMusicaDTO.toDTO(get(id));
    }

    public ResponseMusicaDTO edit(Integer id, EditMusicaDTO dto) {
        Musica musicaDB = get(id);

        if (dto.getTitulo() != null) musicaDB.setTitulo(dto.getTitulo());
        if (dto.getDuracaoSegundos() != null) musicaDB.setDuracaoSegundos(dto.getDuracaoSegundos());
        if (dto.getNumeroFaixa() != null) musicaDB.setNumeroFaixa(dto.getNumeroFaixa());
        if (dto.getTotalReproducoes() != null) musicaDB.setTotalReproducoes(dto.getTotalReproducoes());

        musicaDB = musicaRepository.save(musicaDB);
        return ResponseMusicaDTO.toDTO(musicaDB);
    }

    public void delete(Integer id) {
        Musica musica = get(id);
        musicaRepository.delete(musica);
    }

    public ResponseMusicaDTO reproduzir(Integer idMusica, Integer idUsuario) {
        Usuario usuario = usuarioService.get(idUsuario);
        if (!usuario.getAtivo()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário está inativo");
        }

        Musica musica = get(idMusica);
        musica.setTotalReproducoes(musica.getTotalReproducoes() + 1);

        musica = musicaRepository.save(musica);
        return ResponseMusicaDTO.toDTO(musica);
    }

    public List<TopMusicasDTO> gerarTop10() {
        return musicaRepository.findAll().stream()
                .sorted((m1, m2) -> m2.getTotalReproducoes().compareTo(m1.getTotalReproducoes()))
                .limit(10)
                .map(m -> new TopMusicasDTO(m.getTitulo(), m.getArtista().getNome(), m.getTotalReproducoes()))
                .collect(Collectors.toList());
    }
}