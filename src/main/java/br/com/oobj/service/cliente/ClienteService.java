package br.com.oobj.service.cliente;

import br.com.oobj.model.Cliente;
import br.com.oobj.service.base.DAO;
import br.com.oobj.service.base.Service;

public class ClienteService extends Service<Cliente> {

	private ClienteDAO dao;
	
	public ClienteService() {
		super();
		dao = new ClienteDAO(entityManager);
	}

	@Override
	protected DAO<Cliente> getDAO() {
		return dao;
	}
}
