package com.mortesubita.service;

import com.mortesubita.controller.exceptions.ClubeJaExisteNotFound;
import com.mortesubita.domain.Campeonato;
import com.mortesubita.domain.Clube;
import com.mortesubita.domain.dtos.ClubeDTO;
import com.mortesubita.domain.dtos.ClubePostDTO;
import com.mortesubita.controller.exceptions.CampeonatoNotFound;
import com.mortesubita.repository.CampeonatoRepository;
import com.mortesubita.repository.ClubeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ClubeService {

    @Autowired
    private ClubeRepository clubeRepository;

    @Autowired
    private CampeonatoRepository campeonatoRepository;


    public List<ClubeDTO> listarTodosOsClubes(){
        return this.clubeRepository.findAll().stream().map(a-> new ClubeDTO(a)).collect(Collectors.toList());
    }

    public ClubeDTO adicionarClube(ClubePostDTO dto){
        Campeonato campeonato = null;

        if(this.clubeRepository.findAll().stream().filter(c -> c.equals(new Clube(dto))).collect(Collectors.toList()).size() > 0){
            throw new ClubeJaExisteNotFound("Esse clube ja existe");
        }

        Clube clube = this.clubeRepository.save(new Clube(dto));

        if(dto.getCampeonato() != null){
            campeonato = this.campeonatoRepository.findById(dto.getCampeonato()).orElseThrow(() -> new CampeonatoNotFound("Campeonato não existe!"));
            List<Clube> clubes = campeonato.getClubes();

            if( clubes.stream().filter(time -> time.equals(clube)).collect(Collectors.toList()).size() == 0) {
                clubes.add(clube);
                campeonato.setClubes(clubes);
            }
            //this.campeonatoRepository.save(campeonato);
        }
        return new ClubeDTO(clube);
    }

}
