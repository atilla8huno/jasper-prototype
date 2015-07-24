package br.com.oobj.util.builder;

import br.com.oobj.model.Cliente;

public class ClienteBuilder {

	private Cliente cliente = new Cliente();
	
	public ClienteBuilder comNome(String nome) {
		cliente.setNome(nome);
		return this;
	}
	
	public ClienteBuilder moraNoEndereco(String endereco) {
		cliente.setEndereco(endereco);
		return this;
	}
	
	public ClienteBuilder comCPF(String cpf) {
		cliente.setCpf(cpf);
		return this;
	}
	
	public ClienteBuilder comTelefone(String telefone) {
		cliente.setTelefone(telefone);
		return this;
	}
	
	public Cliente construir() {
		return cliente;
	}
}
