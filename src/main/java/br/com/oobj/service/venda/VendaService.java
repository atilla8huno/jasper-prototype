package br.com.oobj.service.venda;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import br.com.oobj.model.Venda;
import br.com.oobj.service.base.DAO;
import br.com.oobj.service.base.Service;
import br.com.oobj.util.GeradorRelatorio;

public class VendaService extends Service<Venda> {

	private VendaDAO dao;
	
	public VendaService() {
		super();
		dao = new VendaDAO(entityManager);
	}

	public List<Map<String, Object>> consultarDetalhesDaVenda(Integer idVenda) {
		List<Map<String, Object>> detalhes = null;
		try {
			detalhes = dao.consultarVendaParaRelatorio(idVenda);
			List<Map<String, Object>> produtos = consultarProdutosDaVenda(idVenda);
			detalhes.get(0).put("produtos", new JRBeanCollectionDataSource(produtos));
			detalhes.get(0).put("total_itens", produtos.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return detalhes;
	}
	
	public void gerarRelatorioDetalhesDaVenda(Integer idVenda) {
        final String arquivo = "/jasper/Venda.jasper";
        final String subrelatorio = "/jasper/ProdutosSub.jasper";
        
        try {
        	JasperReport relatorio =  (JasperReport) JRLoader.loadObject(getClass().getResourceAsStream(arquivo));
        	JasperReport subRelatorio =  (JasperReport) JRLoader.loadObject(getClass().getResourceAsStream(subrelatorio));
            
        	Map<String, Object> parametros = new HashMap<String, Object>();
        	parametros.put("JASPER_PRODUTOS", subRelatorio);
        	
            GeradorRelatorio.gerarRelatorioDesktop(relatorio, consultarDetalhesDaVenda(idVenda), parametros);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	public List<Map<String, Object>> consultarProdutosDaVenda(Integer idVenda) {
		try {
			return dao.consultarProdutosDaVenda(idVenda);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	protected DAO<Venda> getDAO() {
		return dao;
	}
}
