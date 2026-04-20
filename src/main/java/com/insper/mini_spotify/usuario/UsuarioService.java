package com.insper.mini_spotify.usuario;

import com.insper.mini_spotify.usuario.dto.EditUsuarioDTO;
import com.insper.mini_spotify.usuario.dto.ResponseUsuarioDTO;
import com.insper.mini_spotify.usuario.dto.SaveUsuarioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public ResponseUsuarioDTO save(SaveUsuarioDTO saveUsuarioDTO) {
        if (usuarioRepository.existsByEmail(saveUsuarioDTO.getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email já cadastrado");
        }
        Usuario usuario = Usuario.toModel(saveUsuarioDTO);
        usuario = usuarioRepository.save(usuario);
        return ResponseUsuarioDTO.toDTO(usuario);
    }

    public Page<ResponseUsuarioDTO> list(String nome, Pageable pageable) {
        if (nome != null) {
            return usuarioRepository
                    .findByNomeContaining(nome, pageable)
                    .map(usuario -> ResponseUsuarioDTO.toDTO(usuario));
        }
        return usuarioRepository
                .findAll(pageable)
                .map(usuario -> ResponseUsuarioDTO.toDTO(usuario));
    }

    public Usuario get(Integer id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));
    }

    public ResponseUsuarioDTO getDTO(Integer id) {
        return ResponseUsuarioDTO.toDTO(get(id));
    }

    public ResponseUsuarioDTO edit(Integer id, EditUsuarioDTO editUsuarioDTO) {
        Usuario usuarioDB = get(id);

        if (editUsuarioDTO.getNome() != null) {
            usuarioDB.setNome(editUsuarioDTO.getNome());
        }
        if (editUsuarioDTO.getTipoPlano() != null) {
            usuarioDB.setTipoPlano(editUsuarioDTO.getTipoPlano());
        }
        if (editUsuarioDTO.getAtivo() != null) {
            usuarioDB.setAtivo(editUsuarioDTO.getAtivo());
        }

        usuarioDB = usuarioRepository.save(usuarioDB);
        return ResponseUsuarioDTO.toDTO(usuarioDB);
    }

    public void delete(Integer id) {
        Usuario usuario = get(id);
        usuarioRepository.delete(usuario);
    }
}