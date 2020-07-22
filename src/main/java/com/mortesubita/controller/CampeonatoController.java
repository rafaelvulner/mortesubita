package com.mortesubita.controller;

import com.mortesubita.domain.Campeonato;
import com.mortesubita.domain.dtos.AdicionarClubeDTO;
import com.mortesubita.domain.dtos.CampeonatoDTO;
import com.mortesubita.domain.dtos.CampeonatoSemClubesDTO;
import com.mortesubita.service.CampeonatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/campeonato")
public class CampeonatoController {

    @Autowired
    private CampeonatoService campeonatoService;

    @GetMapping
    public ResponseEntity<List<CampeonatoDTO>> listarCampeonatos(){
        return ResponseEntity.ok(this.campeonatoService.listarCampeonatosComClubes());
    }

    @GetMapping("/semclubes")
    public ResponseEntity<List<CampeonatoSemClubesDTO>> listarCampeonatosSemClubes(){
        return ResponseEntity.ok(this.campeonatoService.listarCampeonatosSemClubes());
    }

    @PostMapping
    public ResponseEntity<CampeonatoDTO> salvar(@RequestBody CampeonatoDTO dto){

        CampeonatoDTO campeonato = this.campeonatoService.salvar(dto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(campeonato.getId())
                .toUri();

        return ResponseEntity.created(uri).build();

    }
}
