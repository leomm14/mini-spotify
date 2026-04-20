package com.insper.mini_spotify.musica;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MusicaRepository extends JpaRepository<Musica, Integer> {
    Page<Musica> findByTituloContaining(String titulo, Pageable pageable);
}