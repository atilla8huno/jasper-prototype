package br.com.oobj.service.produto;

import java.util.List;
import java.util.Map;

import br.com.oobj.model.Produto;
import br.com.oobj.service.base.DAO;
import br.com.oobj.service.base.Service;
import br.com.oobj.service.venda.VendaService;

public class ProdutoService extends Service<Produto> {

	private ProdutoDAO dao;
	private VendaService vendaService;
	
	public ProdutoService() {
		super();
		dao = new ProdutoDAO(entityManager);
	}
	
	public List<Map<String, Object>> consultarProdutosDaVenda(Integer idVenda) {
		return vendaService.consultarProdutosDaVenda(idVenda);
	}

	@Override
	protected DAO<Produto> getDAO() {
		return dao;
	}
}
