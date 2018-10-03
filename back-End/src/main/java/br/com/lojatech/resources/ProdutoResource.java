package br.com.lojatech.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.lojatech.domain.Produto;
import br.com.lojatech.dto.ProtudoDTO;
import br.com.lojatech.resources.utils.URL;
import br.com.lojatech.services.ProdutoService;

@RestController
@RequestMapping(value="/produtos")
public class ProdutoResource {
	
	@Autowired
	private ProdutoService service;
	
	@RequestMapping(value="/{id}", method= RequestMethod.GET)
	public ResponseEntity<Produto> find(Integer id){
		Produto categoria = service.buscar(id);
		return ResponseEntity.ok().body(categoria);
		
	}

	@RequestMapping(method= RequestMethod.GET)
	public ResponseEntity<Page<ProtudoDTO>> findPage(
			@RequestParam(value="nome", defaultValue="") String nome,
			@RequestParam(value="page", defaultValue="0") String categorias,
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="0") String orderBy,
			@RequestParam(value="direction", defaultValue="0") String direction){
		String nomeDecode = URL.decondeParam(nome);
		List<Integer> ids = URL.decodeIntList(categorias);
		Page<Produto> categoriasPage = service.search(nomeDecode,ids,page,linesPerPage,orderBy, direction);
		Page<ProtudoDTO> produtoDto = categoriasPage.map(obj -> new ProtudoDTO(obj));
		
		return ResponseEntity.ok().body(produtoDto);
	}
	
	
	
}
