package br.com.oobj.service.produto;

import javax.persistence.EntityManager;

import br.com.oobj.model.Produto;
import br.com.oobj.service.base.DAO;

public class ProdutoDAO extends DAO<Produto> {

	public ProdutoDAO(EntityManager entityManager) {
		super(entityManager);
	}

}
