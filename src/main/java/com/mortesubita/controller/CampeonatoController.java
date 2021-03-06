package com.mortesubita.controller;

import com.mortesubita.domain.Campeonato;
import com.mortesubita.domain.Clube;
import com.mortesubita.domain.Response;
import com.mortesubita.domain.dtos.AdicionarClubeDTO;
import com.mortesubita.domain.dtos.CampeonatoDTO;
import com.mortesubita.domain.dtos.CampeonatoSemClubesDTO;
import com.mortesubita.domain.dtos.ClubeDTO;
import com.mortesubita.service.CampeonatoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/campeonato")
@ApiOperation(value = "Controla os campeonatos")
@CrossOrigin("*")
public class CampeonatoController {

    @Autowired
    private CampeonatoService campeonatoService;

    @Autowired
    private Response response;

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna a lista de campeonatos com todos os clubes vinculados")
    })
    @GetMapping
    public ResponseEntity<Response<CampeonatoDTO>> listarCampeonatos(){


        this.response.setData(this.campeonatoService.listarCampeonatosComClubes());
        return ResponseEntity.ok(this.response);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna a lista de campeonatos sem os clubes")
    })
    @GetMapping("/semclubes")
    public ResponseEntity<Response<List<CampeonatoSemClubesDTO>>> listarCampeonatosSemClubes(){
        Response<List<CampeonatoSemClubesDTO>> resp = new Response<>();

        resp.setData(this.campeonatoService.listarCampeonatosSemClubes());
        return ResponseEntity.ok(resp);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Cria um campeonato novo")
    })
    @PostMapping
    public ResponseEntity<CampeonatoDTO> salvar(@RequestBody CampeonatoDTO dto){

        CampeonatoDTO campeonato = this.campeonatoService.salvar(dto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(campeonato.getId())
                .toUri();

        return ResponseEntity.created(uri).build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCampeonato(@PathVariable("id") Integer id){
        this.campeonatoService.deletarCampeonato(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CampeonatoDTO> salvar(@PathVariable("id") Integer id, @RequestBody List<ClubeDTO> dto){

        return ResponseEntity.ok(this.campeonatoService.removerClubeDoCampeonato(id, dto));

    }
}
