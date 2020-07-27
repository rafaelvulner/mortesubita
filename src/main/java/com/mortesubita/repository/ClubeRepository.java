package com.mortesubita.repository;

import com.mortesubita.domain.Campeonato;
import com.mortesubita.domain.Clube;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClubeRepository extends JpaRepository<Clube, Integer> {

}
