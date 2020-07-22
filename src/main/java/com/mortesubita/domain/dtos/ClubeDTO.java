package com.mortesubita.domain.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mortesubita.domain.Clube;
import lombok.*;

import javax.persistence.Transient;

@ToString
public class ClubeDTO {

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
    @JsonIgnore
    public Integer campeonato;

    public ClubeDTO(Clube clube) {
        this.id = clube.getId();
        this.nome = clube.getNome();
        this.estado = clube.getEstado();
        this.serie = clube.getSerie();
    }

    public ClubeDTO(){}
}
