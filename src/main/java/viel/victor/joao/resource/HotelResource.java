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

import viel.victor.joao.model.Hotel;
import viel.victor.joao.repository.HotelRepository;
import viel.victor.joao.service.HotelService;

@RestController
@RequestMapping("/hoteis")
public class HotelResource {

	@Autowired
	private HotelRepository hotelRepository;
	
	@Autowired
	private HotelService hotelService;
	
	@GetMapping
	public List<Hotel> listarTodos() {
		return hotelRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Hotel buscarPorId(@PathVariable Long id) {
		return hotelService.buscarHotelPorId(id);
	}
	
	@PostMapping
	public ResponseEntity<Hotel> novoHotel(@Valid @RequestBody Hotel hotel) {
		Hotel hotelSalvo = hotelRepository.save(hotel);
		return ResponseEntity.status(HttpStatus.CREATED).body(hotelSalvo);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Hotel> alterarHotel(@Valid @RequestBody Hotel hotel, @PathVariable Long id) {
		Hotel hotelSalvo = hotelService.atualizarHotel(hotel, id);
		return ResponseEntity.ok().body(hotelSalvo);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void removerHotel(@PathVariable Long id) {
		hotelRepository.deleteById(id);
	}
}
