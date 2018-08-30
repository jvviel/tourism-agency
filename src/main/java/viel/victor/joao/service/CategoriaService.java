package viel.victor.joao.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import viel.victor.joao.model.Categoria;
import viel.victor.joao.repository.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Categoria buscarCategoriaPorId(Long id) {
		Optional<Categoria> categoriaOptional = categoriaRepository.findById(id);
		Categoria categoria = categoriaOptional.orElseThrow(() -> new EmptyResultDataAccessException(1));
		return categoria;
	}
	
	public Categoria alterarCategoria(Long id, Categoria categoria) {
		Categoria categoriaSalva = buscarCategoriaPorId(id);
		BeanUtils.copyProperties(categoria, categoriaSalva, "id");
		return categoriaRepository.save(categoriaSalva);
	}
}
