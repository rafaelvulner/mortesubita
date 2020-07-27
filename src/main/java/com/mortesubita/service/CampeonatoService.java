package com.mortesubita.service;

import com.mortesubita.domain.Campeonato;
import com.mortesubita.domain.Clube;
import com.mortesubita.domain.dtos.CampeonatoDTO;
import com.mortesubita.domain.dtos.CampeonatoSemClubesDTO;
import com.mortesubita.domain.dtos.ClubeDTO;
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

        Campeonato campeonato = null;
        Campeonato campeonatoSalvar = null;

        if(this.campeonatoRepository.findAll().size() > 0){
            if(campeonatoDTO.getId() == null){
                campeonatoSalvar = new Campeonato(campeonatoDTO);
            }else{
                campeonato = this.campeonatoRepository.findById(campeonatoDTO.getId()).get();
                 campeonatoSalvar = new Campeonato(campeonatoDTO);
                List<Clube> clubes = campeonato.getClubes();

                List<ClubeDTO> clubeDTOS = campeonatoDTO.getClubes();

                clubeDTOS.forEach(c ->{
                    if( clubes.stream().filter(time -> time.getId().equals(c.getId())).collect(Collectors.toList()).size() == 0) {
                        clubes.add(new Clube(c));
                    }
                });

                campeonatoSalvar.setClubes(clubes);

            }
        }

        return new CampeonatoDTO(this.campeonatoRepository.save(campeonatoSalvar));

    }

    public CampeonatoDTO removerClubeDoCampeonato(Integer id, List<ClubeDTO> clubes){
        Campeonato campeonato = null;

        campeonato = this.campeonatoRepository.findById(id).get();
        List<Clube> clubes1 = clubes.stream().map(c -> new Clube(c)).collect(Collectors.toList());

        //campeonato.getClubes().removeAll(clubes1);



        List<Clube> clubs = campeonato.getClubes();

         clubes1.forEach( c ->{
             clubs.removeIf(clube -> clube.getId() == c.getId());
         });

        campeonato.setClubes(clubs);

        return new CampeonatoDTO(campeonato);

    }


    public void deletarCampeonato(Integer id){
        this.campeonatoRepository.deleteById(id);
    }

}
