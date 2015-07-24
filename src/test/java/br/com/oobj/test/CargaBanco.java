package br.com.oobj.test;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import br.com.oobj.model.Cliente;
import br.com.oobj.model.Produto;
import br.com.oobj.model.Venda;
import br.com.oobj.service.cliente.ClienteService;
import br.com.oobj.service.produto.ProdutoService;
import br.com.oobj.service.venda.VendaService;
import br.com.oobj.util.builder.ClienteBuilder;
import br.com.oobj.util.builder.ProdutoBuilder;
import br.com.oobj.util.builder.VendaBuilder;
import br.com.oobj.util.enums.FormaPagamento;

public class CargaBanco {

	private static ClienteService clienteService = new ClienteService();
	private static ProdutoService produtoService = new ProdutoService();
	private static VendaService vendaService = new VendaService();
	
	public static void main(String[] args) {
		Cliente cliente1 = getNovoCliente("Átilla", "020.020.888-10");
		Cliente cliente2 = getNovoCliente("Silva", "200.300.400-50");
		Cliente cliente3 = getNovoCliente("Barros", "111.777.888-90");
		
		cliente1 = clienteService.salvar(cliente1);
		cliente2 = clienteService.salvar(cliente2);
		cliente3 = clienteService.salvar(cliente3);
		
		Produto produto1 = getNovoProduto("Notebook", 999.99, 23412341234123L);
		Produto produto2 = getNovoProduto("Monitor", 199.99, 1111141234123L);
		Produto produto3 = getNovoProduto("Mouse", 29.99, 23412342345623L);
		Produto produto4 = getNovoProduto("Teclado", 39.99, 2341098764123L);
		Produto produto5 = getNovoProduto("Ventilador", 59.99, 23556777234123L);
		Produto produto6 = getNovoProduto("iPhone 6", 3999.99, 111111234123L);
		Produto produto7 = getNovoProduto("Galaxy S6", 4999.99, 2341234444123L);
		
		produto1 = produtoService.salvar(produto1);
		produto2 = produtoService.salvar(produto2);
		produto3 = produtoService.salvar(produto3);
		produto4 = produtoService.salvar(produto4);
		produto5 = produtoService.salvar(produto5);
		produto6 = produtoService.salvar(produto6);
		produto7 = produtoService.salvar(produto7);
		
		Venda venda1 = getNovaVenda(cliente1, Arrays.asList(produto1, produto2, produto3));
		venda1.setValorTotal(venda1.getValorTotal());
		Venda venda2 = getNovaVenda(cliente2, Arrays.asList(produto4, produto5, produto6));
		venda2.setValorTotal(venda2.getValorTotal());
		Venda venda3 = getNovaVenda(cliente3, Arrays.asList(produto1, produto4, produto7));
		venda3.setValorTotal(venda3.getValorTotal());
		
		venda1 = vendaService.salvar(venda1);
		venda2 = vendaService.salvar(venda2);
		venda3 = vendaService.salvar(venda3);
	}
	
	private static Cliente getNovoCliente(String nome, String cpf) {
		Cliente cliente = new ClienteBuilder()
				.comNome(nome)
				.comCPF(cpf)
				.moraNoEndereco("Rua 88, 559, Setor Sul - Goiânia")
				.comTelefone("9357-3934")
				.construir();
		
		return cliente;
	}
	
	private static Produto getNovoProduto(String descricao, double valor, long codigoBarras) {
		Produto produto = new ProdutoBuilder()
				.comDescricao(descricao)
				.fabricadoPor("Oobj Tecnologia")
				.comCodigoDeBarras(codigoBarras)
				.comValorUnitario(valor)
				.queVenceEm(new Date())
				.construir();
		
		return produto;
	}
	
	private static Venda getNovaVenda(Cliente cliente, List<Produto> produtos) {
		Venda venda = new VendaBuilder()
				.paraOCliente(cliente)
				.comAFormaDePagamento(FormaPagamento.CARTAO_DE_CREDITO)
				.naDataEHora(new Date())
				.comEssaListaDeProdutos(produtos)
				.construir();
		
		return venda;
	}
}
