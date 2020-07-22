package com.mortesubita.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mortesubita.domain.dtos.ClubeDTO;
import com.mortesubita.domain.dtos.ClubePostDTO;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Clube {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique=true)
    private String nome;

    private String estado;

    private String serie;

    @JsonIgnore
    @ManyToMany(mappedBy = "clubes", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private List<Campeonato> campeonatos;

    public Clube(Integer id, String nome, String estado, String serie) {
        this.id = id;
        this.nome = nome;
        this.estado = estado;
        this.serie = serie;
    }

    public Clube(){}

    public Clube(ClubeDTO dto){
        this.id = dto.getId();
        this.nome = dto.getNome();
        this.estado = dto.getEstado();
        this.serie = dto.getSerie();
    }

    public Clube(ClubePostDTO dto) {
        this.id = dto.getId();
        this.nome = dto.getNome();
        this.estado = dto.getEstado();
        this.serie = dto.getSerie();
    }
}
