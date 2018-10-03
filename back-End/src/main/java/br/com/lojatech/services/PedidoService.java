package br.com.lojatech.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.lojatech.domain.ItemPedido;
import br.com.lojatech.domain.PagamentoComBoleto;
import br.com.lojatech.domain.Pedido;
import br.com.lojatech.domain.Produto;
import br.com.lojatech.domain.enums.EstadoPagamento;
import br.com.lojatech.repository.PagamentoRepository;
import br.com.lojatech.repository.PedidoRepository;
import br.com.lojatech.repository.ProdutoRepository;
import br.com.lojatech.services.exception.ObjectNotFoundException;


@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repository;
	
	@Autowired
	private BoletoService boletoService;
	
	@Autowired
	private PagamentoRepository PagtoRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	public Pedido buscar(int id) {
		Optional<Pedido> categoria = repository.findById(id); 
		return categoria.orElseThrow(()-> new ObjectNotFoundException("Objeto não encontrado! Id "+
				id + ", Tipo: " + categoria.getClass().getName()));
	}

	public  Pedido insert(Pedido pedido) {
		pedido.setId(null);
		pedido.setInstante(new Date());
		pedido.getPagamento().setEstado(EstadoPagamento.PEDENTE);
		pedido.getPagamento().setPedido(pedido);
		
		if(pedido.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagto  = (PagamentoComBoleto) pedido.getPagamento();
			boletoService.preencherPagamentoComBoleto(pagto, pedido.getInstante());
		}
		
		pedido = repository.save(pedido);
		PagtoRepository.save(pedido.getPagamento());
		
		for(ItemPedido ip : pedido.getPedidos()) {
			ip.setDesconto(0.0);
			ip.setPreco(produtoRepository.findById(ip.getPedido().getId()).get().getValor());
			ip.setPedido(pedido);
		}
			
		
		return pedido;
	}
}
