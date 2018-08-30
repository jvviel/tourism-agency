package viel.victor.joao.resource;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import viel.victor.joao.model.Categoria;
import viel.victor.joao.repository.CategoriaRepository;
import viel.victor.joao.service.CategoriaService;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private CategoriaService categoriaService;
	
	@GetMapping
	public List<Categoria> buscarTodos() {
		return categoriaRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Categoria buscarPorId(@PathVariable Long id) {
		return categoriaService.buscarCategoriaPorId(id);
	}
	
	@PostMapping
	public ResponseEntity<Categoria> novaCategoria(@Valid @RequestBody Categoria categoria) {
		Categoria categoriaSalva = categoriaRepository.save(categoria);
		return ResponseEntity.status(HttpStatus.CREATED).body(categoriaSalva);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Categoria> alterarCategoria(@Valid @RequestBody Categoria categoria,
			@PathVariable Long id) {
		Categoria categoriaSalva = categoriaService.alterarCategoria(id, categoria);
		return ResponseEntity.ok().body(categoriaSalva);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void removerCategoria(@PathVariable Long id) {
		categoriaRepository.deleteById(id);
	}
}
