package com.mortesubita.domain.dtos;

import com.mortesubita.domain.Campeonato;
import lombok.Data;

@Data
public class CampeonatoSemClubesDTO {

    private Integer id;

    private String nome;

    public CampeonatoSemClubesDTO(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public CampeonatoSemClubesDTO(Campeonato camp) {
        this.id = camp.getId();
        this.nome = camp.getNome();
    }
}
