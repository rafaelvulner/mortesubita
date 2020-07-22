package com.mortesubita.domain.dtos;

import com.mortesubita.domain.Campeonato;
import lombok.Data;

@Data
public class CampeonatoSemClubesDTO {

    private Integer id;

    private String nome;

    private Integer ano;

    private String nacionalidade;

    public CampeonatoSemClubesDTO(Integer id, String nome, Integer ano) {
        this.id = id;
        this.nome = nome;
        this.ano = ano;
    }

    public CampeonatoSemClubesDTO(Campeonato camp) {
        this.id = camp.getId();
        this.nome = camp.getNome();
        this.ano = camp.getAno();
        this.nacionalidade = camp.getNacionalidade();
    }
}
