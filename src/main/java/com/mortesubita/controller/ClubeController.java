package com.mortesubita.controller;

import com.mortesubita.domain.Clube;
import com.mortesubita.domain.Response;
import com.mortesubita.domain.dtos.CampeonatoDTO;
import com.mortesubita.domain.dtos.ClubeDTO;
import com.mortesubita.domain.dtos.ClubePostDTO;
import com.mortesubita.service.ClubeService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/clube")
@ApiOperation(value = "Controla os clubes")
@CrossOrigin("*")
public class ClubeController {

    @Autowired
    private ClubeService clubeService;

    @Autowired
    private Response response;

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna a lista de clubes")
    })
    @GetMapping
    public ResponseEntity<Response<List<ClubeDTO>>> listar(){
        response.setData(this.clubeService.listarTodosOsClubes());
        return ResponseEntity.ok(this.response);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Cria um clube e adiciona a um campeonato")
    })
    @PostMapping
    public ResponseEntity<ClubeDTO> salvar(@Valid @RequestBody ClubePostDTO dto){

        ClubeDTO clubeDTO = this.clubeService.adicionarClube(dto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(clubeDTO.getId())
                .toUri();

        return ResponseEntity.created(uri).build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarClube(@PathVariable("id") Integer id){
        this.clubeService.deletarCampeonato(id);
        return ResponseEntity.noContent().build();
    }
}
