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

import viel.victor.joao.model.Despesas;
import viel.victor.joao.repository.DespesasRepository;
import viel.victor.joao.service.DespesasService;

@RestController
@RequestMapping("/despesas")
public class DespesasResource {

	@Autowired
	private DespesasRepository despesasRepository;
	
	@Autowired
	private DespesasService despesasService;
	
	@GetMapping
	public List<Despesas> listarTodos() {
		return despesasRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Despesas buscarPorId(@PathVariable Long id) {
		return despesasService.buscarDespesaPorId(id);
	}
	
	@PostMapping
	public ResponseEntity<Despesas> gravarDespesa(@Valid @RequestBody Despesas despesas) {
		Despesas despesaSalva = despesasRepository.save(despesas);
		return ResponseEntity.status(HttpStatus.CREATED).body(despesaSalva);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Despesas> alterarDespesa(@PathVariable Long id,
			@Valid @RequestBody Despesas despesas) {
		Despesas despesaSalva = despesasService.atualizarDespesas(id, despesas);
		return ResponseEntity.ok(despesaSalva);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void removerDespesa(@PathVariable Long id) {
		despesasRepository.deleteById(id);
	}
}
