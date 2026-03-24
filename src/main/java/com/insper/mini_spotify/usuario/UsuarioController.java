package com.insper.mini_spotify.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/usuarios")
    public Collection<Usuario> getUsuarios() {return usuarioService.listarUsuarios();}

    @GetMapping("/usuarios/{id}")
    public Usuario getUsuario(@PathVariable Long id) {return usuarioService.getUsuario(id);}

    @PostMapping("/usuarios")
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario saveUsuario(@RequestBody Usuario usuario) {return usuarioService.cadastrarUsuario(usuario);}

    @PutMapping("/usuarios/{id}")
    public Usuario updateUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {return usuarioService.updateUsuario(id, usuario);}

    @DeleteMapping("/usuarios/{id}")
    public void deleteUsuario(@PathVariable Long id) {usuarioService.deleteUsuario(id);}

}