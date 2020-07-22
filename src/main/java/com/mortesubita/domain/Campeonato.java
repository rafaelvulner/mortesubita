package com.mortesubita.domain;

import com.mortesubita.domain.dtos.CampeonatoDTO;
import com.mortesubita.domain.dtos.CampeonatoSemClubesDTO;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Campeonato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name="campeonato_clubes", joinColumns= {@JoinColumn(name="campeonato_id")}, inverseJoinColumns= {@JoinColumn(name="clube_id")})
    private List<Clube> clubes;

    public Campeonato(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Campeonato(CampeonatoSemClubesDTO camp) {
        this.id = camp.getId();
        this.nome = camp.getNome();
    }

    public Campeonato(CampeonatoDTO campeonatoDTO) {
        this.id = campeonatoDTO.getId();
        this.nome = campeonatoDTO.getNome();
    }



    public Campeonato(){}
}
