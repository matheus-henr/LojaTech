package com.matheushenriquebc.lojatech;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.matheushenriquebc.lojatech.domain.Categoria;
import com.matheushenriquebc.lojatech.domain.Produto;
import com.matheushenriquebc.lojatech.repository.CategoriaRepository;
import com.matheushenriquebc.lojatech.repository.ProdutoRepository;

@SpringBootApplication
public class LojaTechApplication{
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;

	public static void main(String[] args) {
		SpringApplication.run(LojaTechApplication.class, args);
	}

/*	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Informatica");
		Categoria cat2 = new Categoria(null, "Escritorio");
		
		Produto pro1 = new Produto(null, 2000.0, "Computador");
		Produto pro2 = new Produto(null, 800.0, "Impressora");
		Produto pro3 = new Produto(null, 50.0, "Mouse");
		
		
		cat1.getProdutos().addAll(Arrays.asList(pro1, pro2, pro3));
		cat2.getProdutos().addAll(Arrays.asList(pro2));
		
		pro1.getCategorias().addAll(Arrays.asList(cat1));
		pro2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		pro3.getCategorias().addAll(Arrays.asList(cat1));
		
		
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2));
		produtoRepository.saveAll(Arrays.asList(pro1,pro2,pro3));
		
	}*/
}
