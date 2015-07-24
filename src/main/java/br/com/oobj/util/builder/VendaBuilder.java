package br.com.oobj.util.builder;

import java.util.Date;
import java.util.List;

import br.com.oobj.model.Cliente;
import br.com.oobj.model.Produto;
import br.com.oobj.model.Venda;
import br.com.oobj.util.enums.FormaPagamento;

public class VendaBuilder {

	private Venda venda = new Venda();
	
	public VendaBuilder comValorTotal(double valorTotal) {
		venda.setValorTotal(valorTotal);
		return this;
	}
	
	public VendaBuilder paraOCliente(Cliente cliente) {
		venda.setCliente(cliente);
		return this;
	}
	
	public VendaBuilder comProduto(Produto produto) {
		venda.getProdutos().add(produto);
		return this;
	}
	
	public VendaBuilder comEssaListaDeProdutos(List<Produto> produtos) {
		venda.getProdutos().addAll(produtos);
		return this;
	}
	
	public VendaBuilder comAFormaDePagamento(FormaPagamento formaPagamento) {
		venda.setFormaPagamento(formaPagamento.getDescricao());
		return this;
	}
	
	public VendaBuilder naDataEHora(Date dataHoraVenda) {
		venda.setDataHoraVenda(dataHoraVenda);
		return this;
	}
	
	public Venda construir() {
		return venda;
	}
}
