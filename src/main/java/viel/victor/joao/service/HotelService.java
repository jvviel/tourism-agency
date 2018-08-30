package viel.victor.joao.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import viel.victor.joao.model.Hotel;
import viel.victor.joao.repository.HotelRepository;

@Service
public class HotelService {

	@Autowired
	private HotelRepository hotelRepository;
	
	public Hotel buscarHotelPorId(Long id) {
		Optional<Hotel> hotelOptional = hotelRepository.findById(id);
		Hotel hotel = hotelOptional.orElseThrow(() -> new EmptyResultDataAccessException(1));
		return hotel;
	}
	
	public Hotel atualizarHotel(Hotel hotel, Long id) {
		Hotel hotelSalvo = buscarHotelPorId(id);
		BeanUtils.copyProperties(hotel, hotelSalvo, "id");
		return hotelRepository.save(hotelSalvo);
	}
}
