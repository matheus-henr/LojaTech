package br.com.lojatech.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.lojatech.domain.ItemPedido;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Integer> {

}
