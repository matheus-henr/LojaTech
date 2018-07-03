package br.com.lojatech.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Produto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private Double valor;
	private String nome;
	
	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "Produto_Categoria", joinColumns =
	 			@JoinColumn(name = "produto_id"),
	 			inverseJoinColumns = @JoinColumn(name = "categoria_id"))
	private List<Categoria> categorias;
	
	@JsonIgnore
	@OneToMany(mappedBy="id.produto")
	private Set<ItemPedido> itens = new HashSet<>();
	
	public Produto(Integer id, Double valor, String nome) {
		super();
		this.id = id;
		this.valor = valor;
		this.nome = nome;
		setCategorias(new ArrayList<>());
		
	}
	
	public Produto(){
		setCategorias(new ArrayList<>());
	}
	
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}
	
	public int getId() {
		return id;
	}
	
	public Set<ItemPedido> getPedidos() {
		return itens;
	}

	public void setPedidos(Set<ItemPedido> itens) {
		this.itens = itens;
	}
	
	@JsonIgnore
	public List<Pedido> getPedido(){
		List<Pedido> lista = new ArrayList<>();
		for(ItemPedido p : itens) {
			lista.add(p.getPedido());
		}
		return lista;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		if (id != other.id)
			return false;
		return true;
	}

	
	
	


	
}

