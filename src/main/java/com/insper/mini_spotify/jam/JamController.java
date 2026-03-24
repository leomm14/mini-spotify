package com.insper.mini_spotify.jam;

import com.insper.mini_spotify.jam.Jam;
import com.insper.mini_spotify.jam.JamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class JamController {

    @Autowired
    private JamService jamService;

    @GetMapping("/jams")
    public Collection<Jam> getJams() {return jamService.listarJams();}

    @GetMapping("/jams/{id}")
    public Jam getJam(@PathVariable Long id) {return jamService.getJam(id);}

    @PostMapping("/jams")
    @ResponseStatus(HttpStatus.CREATED)
    public Jam saveJam(@RequestBody Jam jam) {return jamService.cadastrarJam(jam);}

    @PutMapping("/jams/{id}")
    public Jam updateJam(@PathVariable Long id, @RequestBody Jam jam) {return jamService.updateJam(id, jam);}

    @DeleteMapping("/jams/{id}")
    public void deleteJam(@PathVariable Long id) {jamService.deleteJam(id);}

}