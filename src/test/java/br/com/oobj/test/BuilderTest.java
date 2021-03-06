package br.com.oobj.test;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

import br.com.oobj.model.Cliente;
import br.com.oobj.model.Produto;
import br.com.oobj.model.Venda;
import br.com.oobj.util.builder.ClienteBuilder;
import br.com.oobj.util.builder.ProdutoBuilder;
import br.com.oobj.util.builder.VendaBuilder;
import br.com.oobj.util.enums.FormaPagamento;

public class BuilderTest {

	@Test
	public void deveConstruirUmCliente() {
		Cliente cliente = new ClienteBuilder()
				.comNome("�tilla")
				.comCPF("123.123.123-00")
				.moraNoEndereco("Rua 88, 559, Setor Sul - Goi�nia")
				.comTelefone("9357-3934")
				.construir();
		
		assertEquals("�tilla", cliente.getNome());
		assertEquals("123.123.123-00", cliente.getCpf());
		assertEquals("Rua 88, 559, Setor Sul - Goi�nia", cliente.getEndereco());
		assertEquals("9357-3934", cliente.getTelefone());
	}
	
	@Test
	public void deveConstruirUmProduto() {
		Date dataVencimento = Calendar.getInstance().getTime();
		
		Produto produto = new ProdutoBuilder()
				.comDescricao("Sab�o em p�")
				.fabricadoPor("OMO Multia��o")
				.comCodigoDeBarras(1233345765674212312L)
				.comValorUnitario(9.99)
				.queVenceEm(dataVencimento)
				.construir();
		
		assertEquals("Sab�o em p�", produto.getDescricao());
		assertEquals("OMO Multia��o", produto.getFabricante());
		assertEquals(new Long(1233345765674212312L), produto.getCodigoBarras());
		assertEquals(new Double(9.99), produto.getValor());
		assertEquals(dataVencimento, produto.getDataValidade());
	}
	
	@Test
	public void deveConstruirUmaVenda() {
		Calendar calendar = Calendar.getInstance();
		// 20/04/2015 16:30
		calendar.set(2015, Calendar.APRIL, 20, 16, 30);
		Date dataHoraVenda = calendar.getTime();
		
		Produto produto1 = new ProdutoBuilder()
				.comDescricao("Sab�o em p�")
				.fabricadoPor("OMO Multia��o")
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
				.comDescricao("Caf� 1KG")
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
		
		assertEquals(valorTotal, venda.getValorTotal());
		assertEquals(getNovoCliente().getCpf(), venda.getCliente().getCpf());
		assertEquals(FormaPagamento.CARTAO_DE_CREDITO.getDescricao(), venda.getFormaPagamento());
		assertEquals(dataHoraVenda, venda.getDataHoraVenda());
		assertEquals(3, venda.getProdutos().size());
	}
	
	private Cliente getNovoCliente() {
		Cliente cliente = new ClienteBuilder()
				.comNome("�tilla")
				.comCPF("123.123.123-00")
				.moraNoEndereco("Rua 88, 559, Setor Sul - Goi�nia")
				.comTelefone("9357-3934")
				.construir();
		
		return cliente;
	}
}
