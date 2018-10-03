package br.com.lojatech.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.lojatech.domain.Pagamento;

public interface PagamentoRepository extends JpaRepository<Pagamento, Integer> {

	
}
