package viel.victor.joao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import viel.victor.joao.model.Viagem;
import viel.victor.joao.repository.ViagemRepository;

@Service
public class ViagemService {

	@Autowired
	private ViagemRepository viagemRepository;

	public Viagem salvarECalcularPreco(Viagem viagem) {
		if (viagem.getValorTotal() != null) {
			return viagemRepository.save(viagem);
		} else {
			Float valorTotal = viagem.getValorPessoa() * viagem.getQuantidadePessoas();
			Float valorTotalComLucro = (valorTotal * viagem.getMargemLucro())/100;
			viagem.setValorTotal(valorTotalComLucro);
		}
		return viagem;
	}
}
