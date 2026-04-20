package com.insper.mini_spotify.artista;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistaRepository extends JpaRepository<Artista, Integer> {

    Page<Artista> findByNomeContaining(String nome, Pageable pageable);
}
