package com.mortesubita.controller;

import com.mortesubita.domain.Clube;
import com.mortesubita.domain.dtos.CampeonatoDTO;
import com.mortesubita.domain.dtos.ClubeDTO;
import com.mortesubita.domain.dtos.ClubePostDTO;
import com.mortesubita.service.ClubeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/clube")
public class ClubeController {

    @Autowired
    private ClubeService clubeService;

    @GetMapping
    public ResponseEntity<List<ClubeDTO>> listar(){
        return ResponseEntity.ok(this.clubeService.listarTodosOsClubes());
    }

    @PostMapping
    public ResponseEntity<ClubeDTO> salvar(@Valid @RequestBody ClubePostDTO dto){

        ClubeDTO clubeDTO = this.clubeService.adicionarClube(dto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(clubeDTO.getId())
                .toUri();

        return ResponseEntity.created(uri).build();

    }
}
