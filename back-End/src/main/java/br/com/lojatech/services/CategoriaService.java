package br.com.lojatech.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.lojatech.domain.Categoria;
import br.com.lojatech.repository.CategoriaRepository;
import br.com.lojatech.services.exception.ObjectNotFoundException;


@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repository;
	
	public Categoria buscar(int id) {
		Optional<Categoria> categoria = repository.findById(id); 
		return categoria.orElseThrow(()-> new ObjectNotFoundException("Objeto não encontrado! Id "+
				id + ", Tipo: " + categoria.getClass().getName()));
	}
}
