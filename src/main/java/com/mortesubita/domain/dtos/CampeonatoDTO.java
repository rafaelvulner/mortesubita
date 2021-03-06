package com.mortesubita.domain.dtos;

import com.mortesubita.domain.Campeonato;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class CampeonatoDTO {

    private Integer id;

    private String nome;

    private Integer ano;

    private String nacionalidade;

    private List<ClubeDTO> clubes;

    public CampeonatoDTO(Campeonato campeonato) {
        this.id = campeonato.getId();
        this.nome = campeonato.getNome();
        this.clubes = (campeonato.getClubes() != null ? parseClubeParaDTO(campeonato) : null);
        this.ano = campeonato.getAno();
        this.nacionalidade = campeonato.getNacionalidade();
    }

    public CampeonatoDTO(){}

    private List<ClubeDTO> parseClubeParaDTO(Campeonato campeonato) {
        return campeonato.getClubes().stream().map(a -> new ClubeDTO(a)).collect(Collectors.toList());
    }
}
