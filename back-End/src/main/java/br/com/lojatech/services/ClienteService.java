package br.com.lojatech.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.lojatech.domain.Cliente;
import br.com.lojatech.repository.ClienteRepository;
import br.com.lojatech.services.exception.ObjectNotFoundException;


@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;
	
	public Cliente buscar(int id) {
		Optional<Cliente> Cliente = repository.findById(id); 
		return Cliente.orElseThrow(()-> new ObjectNotFoundException("Objeto não encontrado! Id "+
				id + ", Tipo: " + Cliente.getClass().getName()));
	}
	
	
	public Cliente save(Cliente obj) {
		obj.setId(null);
		return repository.save(obj);
	}
}
