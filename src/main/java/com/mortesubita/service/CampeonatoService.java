package com.mortesubita.service;

import com.mortesubita.domain.Campeonato;
import com.mortesubita.domain.dtos.CampeonatoDTO;
import com.mortesubita.domain.dtos.CampeonatoSemClubesDTO;
import com.mortesubita.repository.CampeonatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CampeonatoService {

    @Autowired
    private CampeonatoRepository campeonatoRepository;

    public List<CampeonatoSemClubesDTO> listarCampeonatosSemClubes(){
        return this.campeonatoRepository.findAll().stream().map(a -> new CampeonatoSemClubesDTO(a)).collect(Collectors.toList());
    }

    public List<CampeonatoDTO> listarCampeonatosComClubes(){
        return this.campeonatoRepository.findAll().stream().map(a -> new CampeonatoDTO(a)).collect(Collectors.toList());
    }

    public CampeonatoDTO salvar(CampeonatoDTO campeonatoDTO){

        Campeonato campeonato = new Campeonato(campeonatoDTO);

        return new CampeonatoDTO(this.campeonatoRepository.save(campeonato));

    }


    public void deletarCampeonato(Integer id){
        this.campeonatoRepository.deleteById(id);
    }

}
