package br.com.oobj.util.enums;

public enum FormaPagamento {

	CARTAO_DE_CREDITO("Cart�o de Cr�dito"), 
	CARTAO_DE_DEBITO("Cart�o de D�bito"), 
	CHEQUE("Cheque"), 
	DINHEIRO("Dinheiro");

	private String descricao;

	FormaPagamento(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
}
