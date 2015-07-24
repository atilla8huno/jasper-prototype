package br.com.oobj.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import br.com.oobj.model.base.EntidadeOobj;

@Entity
@Cacheable
public class Venda extends EntidadeOobj {

	private static final long serialVersionUID = 6538266751501058397L;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Produto> produtos;
	
	@ManyToOne(cascade = {CascadeType.ALL})
	private Cliente cliente;
	
	@Column(name = "valor_total", nullable = false, columnDefinition = "NUMERIC(8, 2)")
	private Double valorTotal;
	
	@Column(name = "data_hora")
	private Date dataHoraVenda;
	
	@Column(name = "forma_pagamento", length = 25)
	private String formaPagamento;

	public Venda() {
	}

	public List<Produto> getProdutos() {
		if (produtos == null) {
			produtos = new ArrayList<Produto>();
		}
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Double getValorTotal() {
		Double valorTotal = 0.0;
		for (Produto p : produtos) {
			valorTotal += p.getValor();
		}
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Date getDataHoraVenda() {
		return dataHoraVenda;
	}

	public void setDataHoraVenda(Date dataHoraVenda) {
		this.dataHoraVenda = dataHoraVenda;
	}

	public String getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(String formaPagamento) {
		this.formaPagamento = formaPagamento;
	}
}
