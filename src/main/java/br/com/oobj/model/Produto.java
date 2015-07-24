package br.com.oobj.model;

import java.util.Date;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;

import br.com.oobj.model.base.EntidadeOobj;

@Entity
@Cacheable
public class Produto extends EntidadeOobj {

	private static final long serialVersionUID = -2512977737936332669L;

	@Column(length = 60, nullable = false)
	private String descricao;

	@Column(columnDefinition = "NUMERIC(8, 2)")
	private Double valor;
	
	@Column(length = 60)
	private String fabricante;
	
	@Column(name = "data_validade")
	private Date dataValidade;
	
	@Column(name = "codigo_barras", nullable = false, unique = true)
	private Long codigoBarras;
	
	public Produto() {
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public String getFabricante() {
		return fabricante;
	}

	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}

	public Date getDataValidade() {
		return dataValidade;
	}

	public void setDataValidade(Date dataValidade) {
		this.dataValidade = dataValidade;
	}

	public Long getCodigoBarras() {
		return codigoBarras;
	}

	public void setCodigoBarras(Long codigoBarras) {
		this.codigoBarras = codigoBarras;
	}
}
