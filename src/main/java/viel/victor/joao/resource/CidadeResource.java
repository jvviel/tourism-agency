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

import viel.victor.joao.model.Cidade;
import viel.victor.joao.repository.CidadeRepository;
import viel.victor.joao.service.CidadeService;

@RestController
@RequestMapping("/cidades")
public class CidadeResource {

	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private CidadeService cidadeService;
	
	@GetMapping
	public List<Cidade> listarTodos() {
		return cidadeRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Cidade buscarPorId(@PathVariable Long id) {
		return cidadeService.buscarCidadePorId(id);
	}
	
	@PostMapping
	public ResponseEntity<Cidade> novaCidade(@Valid @RequestBody Cidade cidade) {
		Cidade cidadeSalva = cidadeRepository.save(cidade);
		return ResponseEntity.status(HttpStatus.CREATED).body(cidadeSalva);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Cidade> alterarCidade(@Valid @RequestBody Cidade cidade,
			@PathVariable Long id) {
		Cidade cidadeSalva = cidadeService.alterarCidade(cidade, id);
		return ResponseEntity.ok().body(cidadeSalva);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void removerCidade(@PathVariable Long id) {
		cidadeRepository.deleteById(id);
	}
}
