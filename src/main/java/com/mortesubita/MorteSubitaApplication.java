package com.mortesubita;

import com.mortesubita.domain.Campeonato;
import com.mortesubita.domain.Clube;
import com.mortesubita.repository.CampeonatoRepository;
import com.mortesubita.repository.ClubeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class MorteSubitaApplication implements CommandLineRunner {

	@Autowired
	private ClubeRepository clubeRepository;

	@Autowired
	private CampeonatoRepository campeonatoRepository;

	public static void main(String[] args) {
		SpringApplication.run(MorteSubitaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Campeonato brasileiro = new Campeonato();

		Clube cor = new Clube(null, "Corinthians", "SP", "A");
		Clube pal = new Clube(null, "Palmeiras", "SP", "A");

		//clube.setCampeonatos(Arrays.asList(new Campeonato(1, "Brasileirão 2020")));

		clubeRepository.saveAll(Arrays.asList(cor, pal));

		cor = this.clubeRepository.findById(1).get();
		pal = this.clubeRepository.findById(2).get();

		brasileiro.setId(1);
		brasileiro.setNome("Brasileirão 2020");
		brasileiro.setClubes(Arrays.asList(cor, pal));
		brasileiro.setAno(2020);
		brasileiro.setNacionalidade("Brasileira");

		this.campeonatoRepository.save(brasileiro);
		Campeonato champions = new Campeonato();

		Clube barca = new Clube(null, "Barcelona", "Catalão", "A");
		Clube real = new Clube(null, "Real Madrid", "Madri", "A");

		clubeRepository.saveAll(Arrays.asList(barca, real));

		barca = this.clubeRepository.findById(3).get();
		real = this.clubeRepository.findById(4).get();


		champions.setId(2);
		champions.setNome("Champions league");
		champions.setClubes(Arrays.asList(barca, real));
		champions.setAno(2020);
		champions.setNacionalidade("Europeia");

		this.campeonatoRepository.save(champions);



	}
}
