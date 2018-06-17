package com.matheushenriquebc.lojatech.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.matheushenriquebc.lojatech.domain.Categoria;
import com.matheushenriquebc.lojatech.services.CategoriaService;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {
	
	@Autowired
	private CategoriaService service;
	
	@RequestMapping(value="/{id}", method= RequestMethod.GET)
	public ResponseEntity<Categoria> find(Integer id){
		Categoria categoria = service.buscar(id);
		return ResponseEntity.ok().body(categoria);
		
	}

}
