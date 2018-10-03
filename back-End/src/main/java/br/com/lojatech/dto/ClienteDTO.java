package br.com.lojatech.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import br.com.lojatech.domain.Cliente;
import br.com.lojatech.services.validation.ClienteUpdate;

@ClienteUpdate
public class ClienteDTO implements Serializable {


	private static final long serialVersionUID = 1L;
	
	private Integer id;
	@NotEmpty(message="Preechimento Obrigatorio")
	@Length(min=5, max=120, message="Deve ter entre 5 e 120 caractere")
	private String nome;
	@NotEmpty(message="Preechimento Obrigatorio")
	@Email(message="E-mail invalido")
	private String email;
	
	public ClienteDTO() {}

	public ClienteDTO(Cliente obj) {
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.email = obj.getEmail();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
