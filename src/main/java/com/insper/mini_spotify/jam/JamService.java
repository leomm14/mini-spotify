package com.insper.mini_spotify.jam;

import com.insper.mini_spotify.jam.dto.EditJamDTO;
import com.insper.mini_spotify.jam.dto.ResponseJamDTO;
import com.insper.mini_spotify.jam.dto.SaveJamDTO;
import com.insper.mini_spotify.usuario.Usuario;
import com.insper.mini_spotify.usuario.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class JamService {

    @Autowired
    private JamRepository jamRepository;

    @Autowired
    private UsuarioService usuarioService;

    public ResponseJamDTO save(SaveJamDTO dto) {
        List<Usuario> usuarios = new ArrayList<>();

        for (Integer id : dto.getIdsUsuarios()) {
            Usuario usuario = usuarioService.get(id);
            usuarios.add(usuario);
        }

        Jam jam = Jam.toModel(dto, usuarios);
        jam = jamRepository.save(jam);

        return ResponseJamDTO.toDTO(jam);
    }

    public Page<ResponseJamDTO> list(Pageable pageable) {
        return jamRepository.findAll(pageable)
                .map(jam -> ResponseJamDTO.toDTO(jam));
    }

    public Jam get(Integer id) {
        return jamRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Jam não encontrada"));
    }

    public ResponseJamDTO getDTO(Integer id) {
        return ResponseJamDTO.toDTO(get(id));
    }

    public ResponseJamDTO edit(Integer id, EditJamDTO dto) {
        Jam jamDB = get(id);

        if (dto.getIdsUsuarios() != null) {
            List<Usuario> novosUsuarios = new ArrayList<>();
            for (Integer idUser : dto.getIdsUsuarios()) {
                novosUsuarios.add(usuarioService.get(idUser));
            }
            jamDB.setUsuarios(novosUsuarios);
        }

        jamDB = jamRepository.save(jamDB);
        return ResponseJamDTO.toDTO(jamDB);
    }

    public void delete(Integer id) {
        Jam jam = get(id);
        jamRepository.delete(jam);
    }
}