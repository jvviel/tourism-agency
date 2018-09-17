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

import viel.victor.joao.model.Viagem;
import viel.victor.joao.repository.ViagemRepository;
import viel.victor.joao.service.ViagemService;

@RestController
@RequestMapping("/viagem")
public class ViagemResource {

	@Autowired
	private ViagemRepository viagemRepository;
	
	@Autowired
	private ViagemService viagemService;
	
	@GetMapping
	public List<Viagem> listarTodos() {
		return viagemRepository.findAll();
	}
	
	@GetMapping("/{id}")
	private Viagem buscarPorId(@PathVariable Long id) {
		return viagemService.buscarViagemPorId(id);
	}
	
	@PostMapping
	public ResponseEntity<Viagem> gravarViagem(@Valid @RequestBody Viagem viagem) {
		Viagem viagemSalva = viagemRepository.save(viagem);
		return ResponseEntity.status(HttpStatus.CREATED).body(viagemSalva);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Viagem> alterarViagem(@Valid @RequestBody Viagem viagem,
			@PathVariable Long id) {
		Viagem viagemSalva = viagemService.atualizarViagem(id, viagem);
		return ResponseEntity.ok(viagemSalva);
	}
	
	@PutMapping("/valorTotal")
	public Viagem calcularTotalPorPessoa(@RequestBody Viagem viagem) {
		return viagemService.calcularTotal(viagem);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void removerViagem(@PathVariable Long id) {
		viagemRepository.deleteById(id);
	}
}
