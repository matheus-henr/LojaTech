package com.matheushenriquebc.lojatech.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matheushenriquebc.lojatech.domain.Categoria;
import com.matheushenriquebc.lojatech.repository.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repository;
	
	public Categoria buscar(int id){
		Optional<Categoria> categoria = repository.findById(id);
		return categoria.orElse(null);
	}
}
