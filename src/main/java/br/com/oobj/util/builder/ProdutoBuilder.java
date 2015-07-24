package br.com.oobj.util.builder;

import java.util.Date;

import br.com.oobj.model.Produto;

public class ProdutoBuilder {

	private Produto produto = new Produto();
	
	public ProdutoBuilder comDescricao(String descricao) {
		produto.setDescricao(descricao);
		return this;
	}
	
	public ProdutoBuilder queVenceEm(Date dataVencimento) {
		produto.setDataValidade(dataVencimento);
		return this;
	}
	
	public ProdutoBuilder comCodigoDeBarras(long codigoDeBarras) {
		produto.setCodigoBarras(codigoDeBarras);
		return this;
	}
	
	public ProdutoBuilder comValorUnitario(double valorUnitario) {
		produto.setValor(valorUnitario);
		return this;
	}
	
	public ProdutoBuilder fabricadoPor(String fabricante) {
		produto.setFabricante(fabricante);
		return this;
	}
	
	public Produto construir() {
		return produto;
	}
}
