package br.com.oobj.service.venda;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import br.com.oobj.model.Venda;
import br.com.oobj.service.base.DAO;

public class VendaDAO extends DAO<Venda> {

	public VendaDAO(EntityManager entityManager) {
		super(entityManager);
	}

	@SuppressWarnings("unchecked")
	protected List<Map<String, Object>> consultarVendaParaRelatorio(Integer idVenda) throws Exception {
		Criteria criteria = createCriteria()
				.createAlias("cliente", "c")
				.add(Restrictions.eq("id", idVenda));

		ProjectionList list = Projections.projectionList()
				.add(Projections.property("id").as("id"))
				.add(Projections.property("valorTotal").as("valor_total"))
				.add(Projections.property("formaPagamento").as("forma_pagamento"))
				.add(Projections.property("dataHoraVenda").as("data_hora_venda"))
				.add(Projections.property("c.nome").as("nome_cliente"))
				.add(Projections.property("c.endereco").as("endereco_cliente"))
				.add(Projections.property("c.telefone").as("telefone_cliente"))
				.add(Projections.property("c.cpf").as("cpf_cliente"));
		
		criteria.setProjection(list);

		return criteria.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP).list();
	}
	
	@SuppressWarnings("unchecked")
	protected List<Map<String, Object>> consultarProdutosDaVenda(Integer idVenda) throws Exception {
		Criteria criteria = createCriteria()
				.createAlias("produtos", "p")
				.add(Restrictions.eq("id", idVenda));
		
		ProjectionList list = Projections.projectionList()
				.add(Projections.property("p.descricao").as("descricao"))
				.add(Projections.property("p.valor").as("valor_unitario"))
				.add(Projections.property("p.fabricante").as("fabricante"))
				.add(Projections.property("p.dataValidade").as("data_validade"))
				.add(Projections.property("p.codigoBarras").as("codigo_barras"));
		
		criteria.setProjection(list);

		return criteria.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP).list();
	}
}
