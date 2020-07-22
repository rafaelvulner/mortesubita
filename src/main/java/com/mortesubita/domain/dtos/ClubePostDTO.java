package com.mortesubita.domain.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mortesubita.domain.Clube;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class ClubePostDTO {

    @Getter
    @Setter
    private Integer id;

    @Getter
    @Setter
    private String nome;

    @Getter
    @Setter
    private String estado;

    @Getter
    @Setter
    private String serie;

    @Setter
    @Getter
    public Integer campeonato;

    public ClubePostDTO(Clube clube) {
        this.id = clube.getId();
        this.nome = clube.getNome();
        this.estado = clube.getEstado();
        this.serie = clube.getSerie();
    }

    public ClubePostDTO(){}
}
