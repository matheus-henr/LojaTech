package br.com.lojatech.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.lojatech.domain.Categoria;
import br.com.lojatech.domain.Produto;
import br.com.lojatech.repository.CategoriaRepository;
import br.com.lojatech.repository.ProdutoRepository;
import br.com.lojatech.services.exception.ObjectNotFoundException;


@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository repository;
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Produto buscar(int id) {
		Optional<Produto> categoria = repository.findById(id); 
		return categoria.orElseThrow(()-> new ObjectNotFoundException("Objeto não encontrado! Id "+
				id + ", Tipo: " + categoria.getClass().getName()));
	}
	
	
	public Page<Produto> search(String nome, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy,
			String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction),
				orderBy);
		List<Categoria> categorias = categoriaRepository.findAllById(ids);
		return repository.search(nome,categorias, pageRequest);
	}
}
