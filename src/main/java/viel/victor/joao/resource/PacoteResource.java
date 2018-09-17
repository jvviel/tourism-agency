package viel.victor.joao.resource;

import java.util.List;

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

import viel.victor.joao.model.Pacotes;
import viel.victor.joao.repository.PacoteRepository;
import viel.victor.joao.service.PacoteService;

@RestController
@RequestMapping("/pacotes")
public class PacoteResource {

	@Autowired
	private PacoteRepository pacoteRepository;
	
	@Autowired
	private PacoteService pacoteService;

	@GetMapping
	public List<Pacotes> listarTodos() {
		return pacoteRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Pacotes buscarPorId(@PathVariable Long id) {
		return pacoteService.buscarPacote(id);
	}
	
	@PostMapping
	public ResponseEntity<Pacotes> gravarPacotes(@RequestBody Pacotes pacotes) {
		Pacotes pacoteSalvo = pacoteRepository.save(pacotes);
		return ResponseEntity.status(HttpStatus.CREATED).body(pacoteSalvo);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Pacotes> alterarPacotes(@RequestBody Pacotes pacote, @PathVariable Long id) {
		Pacotes pacoteSalvo = pacoteService.atualizarPacote(id, pacote);
		return ResponseEntity.ok().body(pacoteSalvo);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void removerPacote(@PathVariable Long id) {
		pacoteRepository.deleteById(id);
	}
}
