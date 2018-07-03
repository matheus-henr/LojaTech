package br.com.lojatech.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.lojatech.domain.Pedido;
import br.com.lojatech.repository.PedidoRepository;
import br.com.lojatech.services.exception.ObjectNotFoundException;


@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repository;
	
	public Pedido buscar(int id) {
		Optional<Pedido> categoria = repository.findById(id); 
		return categoria.orElseThrow(()-> new ObjectNotFoundException("Objeto não encontrado! Id "+
				id + ", Tipo: " + categoria.getClass().getName()));
	}
}
