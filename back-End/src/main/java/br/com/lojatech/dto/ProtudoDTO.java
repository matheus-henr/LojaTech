package br.com.lojatech.dto;

import java.io.Serializable;

import br.com.lojatech.domain.Produto;

public class ProtudoDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String nome;
	private Double preco;



	public ProtudoDTO() {}
	
	public ProtudoDTO(Produto obj) {
		id = obj.getId();
		nome = obj.getNome();
		preco = obj.getValor();
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



	public Double getPreco() {
		return preco;
	}



	public void setPreco(Double preco) {
		this.preco = preco;
	}
	
	
}
