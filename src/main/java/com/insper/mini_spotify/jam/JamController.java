package com.insper.mini_spotify.jam;

import com.insper.mini_spotify.jam.dto.EditJamDTO;
import com.insper.mini_spotify.jam.dto.ResponseJamDTO;
import com.insper.mini_spotify.jam.dto.SaveJamDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class JamController {

    @Autowired
    private JamService jamService;

    @GetMapping("/jams")
    public Page<ResponseJamDTO> getJams(Pageable pageable) {
        return jamService.list(pageable);
    }

    @GetMapping("/jams/{id}")
    public ResponseJamDTO getJam(@PathVariable Integer id) {
        return jamService.getDTO(id);
    }

    @PostMapping("/jams")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseJamDTO saveJam(@Valid @RequestBody SaveJamDTO jam) {
        return jamService.save(jam);
    }

    @PutMapping("/jams/{id}")
    public ResponseJamDTO updateJam(@PathVariable Integer id, @RequestBody EditJamDTO jam) {
        return jamService.edit(id, jam);
    }

    @DeleteMapping("/jams/{id}")
    public void deleteJam(@PathVariable Integer id) {
        jamService.delete(id);
    }
}