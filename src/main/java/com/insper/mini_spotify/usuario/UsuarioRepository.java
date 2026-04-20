package com.insper.mini_spotify.usuario;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Page<Usuario> findByNomeContaining(String nome, Pageable pageable);

    boolean existsByEmail(String email);
}
