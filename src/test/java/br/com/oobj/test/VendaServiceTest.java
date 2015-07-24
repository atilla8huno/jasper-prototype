package br.com.oobj.test;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import br.com.oobj.model.Cliente;
import br.com.oobj.model.Produto;
import br.com.oobj.model.Venda;
import br.com.oobj.service.venda.VendaService;
import br.com.oobj.util.builder.ClienteBuilder;
import br.com.oobj.util.builder.ProdutoBuilder;
import br.com.oobj.util.builder.VendaBuilder;
import br.com.oobj.util.enums.FormaPagamento;

public class VendaServiceTest {

	private static final Integer ID_VENDA = 11;
	
	@SuppressWarnings("static-access")
	@Test
	public void deveGerarUmRelatorioDeVenda() throws InterruptedException {
		VendaService service = new VendaService();
		service.gerarRelatorioDetalhesDaVenda(ID_VENDA);
		
		// dorme 10 segundos para poder analisar o relatório
		Thread.currentThread().sleep(10000);
	}
	
	@Test
	public void deveConsultarDetalhesDeUmaVenda() {
		VendaService service = new VendaService();
		
		List<Map<String, Object>> detalhes = service.consultarDetalhesDaVenda(ID_VENDA);
		assertNotNull(detalhes);
	}
	
	@Test
	public void deveSalvarConsultarExcluirUmaVenda() {
		Calendar calendar = Calendar.getInstance();
		// 20/04/2015 16:30
		calendar.set(2015, Calendar.APRIL, 20, 16, 30);
		Date dataHoraVenda = calendar.getTime();
		
		Produto produto1 = new ProdutoBuilder()
				.comDescricao("Sabão em pó")
				.fabricadoPor("OMO Multiação")
				.comCodigoDeBarras(1233345765674212312L)
				.comValorUnitario(9.99)
				.queVenceEm(new Date())
				.construir();
		
		Produto produto2 = new ProdutoBuilder()
				.comDescricao("Cerveja Longneck")
				.fabricadoPor("Heineken")
				.comCodigoDeBarras(123300000074212312L)
				.comValorUnitario(3.50)
				.queVenceEm(new Date())
				.construir();
		
		Produto produto3 = new ProdutoBuilder()
				.comDescricao("Café 1KG")
				.fabricadoPor("Moinho Fino")
				.comCodigoDeBarras(1233345765674000000L)
				.comValorUnitario(12.39)
				.queVenceEm(new Date())
				.construir();
		
		Double valorTotal = produto1.getValor() + produto2.getValor() + produto3.getValor();
		
		Venda venda = new VendaBuilder()
				.paraOCliente(getNovoCliente())
				.comAFormaDePagamento(FormaPagamento.CARTAO_DE_CREDITO)
				.naDataEHora(dataHoraVenda)
				.comProduto(produto1)
				.comEssaListaDeProdutos(Arrays.asList(produto2, produto3))
				.comValorTotal(valorTotal)
				.construir();
		
		// Primeito cria e salva uma venda
		VendaService service = new VendaService();
		venda = service.salvar(venda);
		assertNotNull(venda);
		assertNotEquals(new Integer(0), venda.getId());
		
		// Agora consulta no banco
		venda = service.consultarPorId(venda.getId());
		assertNotNull(venda);
		assertNotEquals(new Integer(0), venda.getId());

		// Agora exclui
		service.excluir(venda);
		venda = service.consultarPorId(venda.getId());
		assertNull(venda);
	}
	
	private Cliente getNovoCliente() {
		Cliente cliente = new ClienteBuilder()
				.comNome("Átilla")
				.comCPF("123.123.123-00")
				.moraNoEndereco("Rua 88, 559, Setor Sul - Goiânia")
				.comTelefone("9357-3934")
				.construir();
		
		return cliente;
	}
}
