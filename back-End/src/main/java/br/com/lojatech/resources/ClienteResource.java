package br.com.lojatech.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.lojatech.domain.Cliente;
import br.com.lojatech.dto.ClienteDTO;
import br.com.lojatech.dto.ClienteNewDTO;
import br.com.lojatech.services.ClienteService;

@RestController
@RequestMapping(value="/clientes")
public class ClienteResource {
	
	@Autowired
	private ClienteService service;
	
	@RequestMapping(value="/{id}", method= RequestMethod.GET)
	public ResponseEntity<Cliente> find(Integer id){
		Cliente categoria = service.find(id);
		return ResponseEntity.ok().body(categoria);
		
	}
	
	@RequestMapping(method= RequestMethod.POST)
	public ResponseEntity<Void> save(@Valid @RequestBody ClienteNewDTO objDto){
		Cliente  obj = service.fromDTO(objDto); 
		obj = service.save(obj);
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(obj.getId())
				.toUri();
		
		return ResponseEntity.created(uri)
				.build();
	}

	@RequestMapping(method= RequestMethod.GET)
	public ResponseEntity<List<ClienteDTO>> findAll(){
		List<Cliente> categorias = service.findAll();
		List<ClienteDTO> ClienteDto = categorias.stream()
				.map(obj -> new ClienteDTO(obj))
				.collect(Collectors.toList());
		
		return ResponseEntity.ok().body(ClienteDto);
	}
	
	@RequestMapping(value="/page", method= RequestMethod.GET)
	public ResponseEntity<Page<ClienteDTO>> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="0") String orderBy,
			@RequestParam(value="direction", defaultValue="0") String direction){
		Page<Cliente> categorias = service.findPage(page, linesPerPage, orderBy, direction);
		Page<ClienteDTO> ClienteDto = categorias.map(obj -> new ClienteDTO(obj));
		
		return ResponseEntity.ok().body(ClienteDto);
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody ClienteDTO objDto, @PathVariable Integer id){
		Cliente obj = service.fromDTO(objDto);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	

	@RequestMapping(value="/{id}", method= RequestMethod.DELETE)
	public ResponseEntity<Cliente> delete(@PathVariable Integer id){
		service.delete(id);
		return ResponseEntity.noContent().build();
		
	}
}
