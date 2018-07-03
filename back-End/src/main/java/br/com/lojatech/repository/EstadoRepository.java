package br.com.lojatech.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.lojatech.domain.Estado;
@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer>{

	

}
