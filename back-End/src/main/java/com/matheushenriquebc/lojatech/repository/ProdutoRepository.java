package com.matheushenriquebc.lojatech.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.matheushenriquebc.lojatech.domain.Categoria;
import com.matheushenriquebc.lojatech.domain.Produto;
@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer>{

	

}
