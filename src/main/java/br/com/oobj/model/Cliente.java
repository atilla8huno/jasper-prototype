package br.com.oobj.model;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;

import br.com.oobj.model.base.EntidadeOobj;

@Entity
@Cacheable
public class Cliente extends EntidadeOobj {

	private static final long serialVersionUID = 6806083993032267235L;

	@Column(length = 60, nullable = false)
	private String nome;
	
	@Column(length = 120)
	private String endereco;
	
	@Column(length = 16)
	private String telefone;
	
	@Column(nullable = false, unique = true, length = 14)
	private String cpf;

	public Cliente() {
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
}
