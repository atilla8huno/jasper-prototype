package br.com.oobj.service.cliente;

import javax.persistence.EntityManager;

import br.com.oobj.model.Cliente;
import br.com.oobj.service.base.DAO;

public class ClienteDAO extends DAO<Cliente> {

	public ClienteDAO(EntityManager entityManager) {
		super(entityManager);
	}

	
}
