package viel.victor.joao.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import viel.victor.joao.model.Cidade;
import viel.victor.joao.repository.CidadeRepository;

@Service
public class CidadeService {

	@Autowired
	private CidadeRepository cidadeRepository;

	public Cidade buscarCidadePorId(Long id) {
		Optional<Cidade> cidadeOptional = cidadeRepository.findById(id);
		Cidade cidade = cidadeOptional.orElseThrow(() -> new EmptyResultDataAccessException(1));
		return cidade;
	}
	
	public Cidade alterarCidade(Cidade cidade, Long id) {
		Cidade cidadeSalva = buscarCidadePorId(id);
		BeanUtils.copyProperties(cidade, cidadeSalva, "id");
		return cidadeRepository.save(cidadeSalva);
	}
}
