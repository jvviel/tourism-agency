package viel.victor.joao.service;

import java.text.DecimalFormat;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import viel.victor.joao.model.Despesas;
import viel.victor.joao.model.Viagem;
import viel.victor.joao.repository.ViagemRepository;

@Service
public class ViagemService {

	@Autowired
	private ViagemRepository viagemRepository;

	public Viagem buscarViagemPorId(Long id) {
		Optional<Viagem> viagemOptional = viagemRepository.findById(id);
		Viagem viagem = viagemOptional.orElseThrow(() -> new EmptyResultDataAccessException(1));
		return viagem;
	}
	
	public Viagem atualizarViagem(Long id, Viagem viagem) {
		Viagem viagemSalva = buscarViagemPorId(id);
		BeanUtils.copyProperties(viagem, viagemSalva, "id");
		return viagemRepository.save(viagemSalva);
	}
	
	public Viagem calcularTotal(Viagem viagem) {
		Float valorTotal = 0f;
		for (Despesas despesas : viagem.getDespesas()) {
			valorTotal += despesas.getValor();
		}
		Float valorLucro = valorTotal + (valorTotal * viagem.getMargemLucro())/100;
		Float valorPorPessoa = valorLucro / viagem.getQuantidadePessoas();
		DecimalFormat df = new DecimalFormat("0.00");
		Float f = new Float(df.format(valorPorPessoa));
		viagem.setValorPessoa(f);
		
		return viagem;
	}
}
