package br.com.lojatech.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.lojatech.domain.Cidade;
import br.com.lojatech.domain.Cliente;
import br.com.lojatech.domain.Endereco;
import br.com.lojatech.domain.enums.TipoCliente;
import br.com.lojatech.dto.ClienteDTO;
import br.com.lojatech.dto.ClienteNewDTO;
import br.com.lojatech.repository.ClienteRepository;
import br.com.lojatech.repository.EnderecoRepository;
import br.com.lojatech.services.exception.DataIntegrityException;
import br.com.lojatech.services.exception.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;
	@Autowired
	private EnderecoRepository enderecoRepository;

	public Cliente find(int id) {
		Optional<Cliente> Cliente = repository.findById(id);
		return Cliente.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id " + id + ", Tipo: " + Cliente.getClass().getName()));
	}

	@Transactional
	public Cliente save(Cliente obj) {
		obj.setId(null);
		obj = repository.save(obj);
		enderecoRepository.saveAll(obj.getEnderecos());
		return obj;
	}

	public List<Cliente> findAll() {
		return repository.findAll();
	}

	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repository.findAll(pageRequest);
	}

	public Cliente update(Cliente obj) {
		Cliente newObj = find(obj.getId());
		updateData(newObj, obj);
		return repository.save(obj);
	}

	public void delete(Integer id) {
		find(id);
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel deletar porque tem endidades relacionada");
		}
	}

	public Cliente fromDTO(ClienteDTO dto) {
		return new Cliente(dto.getId(), dto.getNome(), dto.getEmail(), null, null);
	}

	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());

	}

	public Cliente fromDTO(ClienteNewDTO obj) {
		Cliente cli = new Cliente(null, obj.getNome(), obj.getEmail(), obj.getCpfOuCnpj(),
				TipoCliente.toEnum(obj.getTipo()));
		Endereco end = new Endereco(null, obj.getLogradouro(), obj.getNome(), obj.getComplemento(), obj.getBairro(),
				obj.getCep(),

				cli, new Cidade(obj.getCidadeId(), null, null));
		cli.getEnderecos().add(end);
		cli.getTelefones().add(obj.getTelefone());

		if (obj.getTelefone2() != null)
			cli.getTelefones().add(obj.getTelefone2());
		if (obj.getTelefone3() != null)
			cli.getTelefones().add(obj.getTelefone3());

		return cli;
	}

}
