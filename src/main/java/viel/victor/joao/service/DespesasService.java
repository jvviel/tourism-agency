package viel.victor.joao.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import viel.victor.joao.model.Despesas;
import viel.victor.joao.repository.DespesasRepository;

@Service
public class DespesasService {

	@Autowired
	private DespesasRepository despesasRepository;
	
	public Despesas buscarDespesaPorId(Long id) {
		Optional<Despesas> despesasOptional = despesasRepository.findById(id);
		Despesas despesas = despesasOptional.orElseThrow(() -> new EmptyResultDataAccessException(1));
		return despesas;
	}
	
	public Despesas atualizarDespesas(Long id, Despesas despesas) {
		Despesas despesaSalva = buscarDespesaPorId(id);
		BeanUtils.copyProperties(despesas, despesaSalva, "id");
		return despesasRepository.save(despesaSalva);
	}
}
