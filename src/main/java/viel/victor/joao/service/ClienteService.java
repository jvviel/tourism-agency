package viel.victor.joao.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import viel.victor.joao.model.Cliente;
import viel.victor.joao.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	public Cliente buscarClientePorId(Long id) {
		Optional<Cliente> clienteOptional = clienteRepository.findById(id);
		Cliente cliente = clienteOptional.orElseThrow(() -> new EmptyResultDataAccessException(1));
		return cliente;
	}
	
	public Cliente atualizarCliente(Cliente cliente, Long id) {
		Cliente clienteSalvo = buscarClientePorId(id);
		BeanUtils.copyProperties(cliente, clienteSalvo, "id");
		return clienteRepository.save(clienteSalvo);
	}
}
