package br.com.lojatech.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.lojatech.domain.Pedido;
@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer>{

	

}
