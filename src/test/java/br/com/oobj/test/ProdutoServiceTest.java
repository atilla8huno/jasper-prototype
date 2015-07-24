package br.com.oobj.test;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Date;

import org.junit.Test;

import br.com.oobj.model.Produto;
import br.com.oobj.service.produto.ProdutoService;
import br.com.oobj.util.builder.ProdutoBuilder;

public class ProdutoServiceTest {

	@Test
	public void deveSalvarConsultarExcluirUmProduto() {
		Produto produto = new ProdutoBuilder()
				.comDescricao("Sabão em pó")
				.fabricadoPor("OMO Multiação")
				.comCodigoDeBarras(1233345765674212312L)
				.comValorUnitario(9.99)
				.queVenceEm(new Date())
				.construir();
		
		// Primeito cria e salva um produto
		ProdutoService service = new ProdutoService();
		produto = service.salvar(produto);
		assertNotNull(produto);
		assertNotEquals(new Integer(0), produto.getId());
		
		// Agora consulta no banco
		produto = service.consultarPorId(produto.getId());
		assertNotNull(produto);
		assertNotEquals(new Integer(0), produto.getId());

		// Agora exclui
		service.excluir(produto);
		produto = service.consultarPorId(produto.getId());
		assertNull(produto);
	}
	
}
