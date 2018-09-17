package viel.victor.joao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import viel.victor.joao.model.Pacotes;

public interface PacoteRepository extends JpaRepository<Pacotes, Long> {
	
}
