package viel.victor.joao.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import viel.victor.joao.model.Pacotes;
import viel.victor.joao.repository.PacoteRepository;

@Service
public class PacoteService {

	@Autowired
	private PacoteRepository pacoteRepository;
	
	public Pacotes buscarPacote(Long id) {
		Optional<Pacotes> pacotesOptional = pacoteRepository.findById(id);
		Pacotes pacote = pacotesOptional.orElseThrow(() -> new EmptyResultDataAccessException(1));
		return pacote;
	}
	
	public Pacotes atualizarPacote(Long id, Pacotes pacote) {
		Pacotes pacotes = buscarPacote(id);
		BeanUtils.copyProperties(pacote, pacotes, "id");
		return pacoteRepository.save(pacotes);
	}
}
