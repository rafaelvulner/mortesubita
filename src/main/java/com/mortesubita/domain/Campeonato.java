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

    private Integer ano;

    private String nacionalidade;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name="campeonato_clubes", joinColumns= {@JoinColumn(name="campeonato_id")}, inverseJoinColumns= {@JoinColumn(name="clube_id")})
    private List<Clube> clubes;

    public Campeonato(Integer id, String nome, String nacionalidade) {
        this.id = id;
        this.nome = nome;
        this.nacionalidade = nacionalidade;
    }

    public Campeonato(CampeonatoSemClubesDTO camp) {
        this.id = camp.getId();
        this.nome = camp.getNome();
        this.ano = camp.getAno();
        this.nacionalidade = camp.getNacionalidade();
    }

    public Campeonato(CampeonatoDTO campeonatoDTO) {
        this.id = campeonatoDTO.getId();
        this.nome = campeonatoDTO.getNome();
        this.ano = campeonatoDTO.getAno();
        this.nacionalidade = campeonatoDTO.getNacionalidade();
    }



    public Campeonato(){}
}
