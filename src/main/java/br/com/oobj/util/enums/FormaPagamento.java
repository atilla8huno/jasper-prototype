package br.com.oobj.util.enums;

public enum FormaPagamento {

	CARTAO_DE_CREDITO("Cartão de Crédito"), 
	CARTAO_DE_DEBITO("Cartão de Débito"), 
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
