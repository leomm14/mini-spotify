package com.insper.mini_spotify.usuario;

import com.insper.mini_spotify.usuario.dto.EditUsuarioDTO;
import com.insper.mini_spotify.usuario.dto.ResponseUsuarioDTO;
import com.insper.mini_spotify.usuario.dto.SaveUsuarioDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public Page<ResponseUsuarioDTO> listUsuarios(
            @RequestParam(required = false) String nome,
            Pageable pageable) {
        return usuarioService.list(nome, pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseUsuarioDTO saveUsuario(@Valid @RequestBody SaveUsuarioDTO usuario) {
        return usuarioService.save(usuario);
    }

    @GetMapping("/{id}")
    public ResponseUsuarioDTO getUsuario(@PathVariable Integer id) {
        return usuarioService.getDTO(id);
    }

    @PutMapping("/{id}")
    public ResponseUsuarioDTO editUsuario(
            @PathVariable Integer id,
            @RequestBody EditUsuarioDTO usuario) {
        return usuarioService.edit(id, usuario);
    }

    @DeleteMapping("/{id}")
    public void deleteUsuario(@PathVariable Integer id) {
        usuarioService.delete(id);
    }
}