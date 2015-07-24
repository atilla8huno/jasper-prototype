package br.com.oobj.test;

import org.junit.Test;

import static org.junit.Assert.*;
import br.com.oobj.model.Cliente;
import br.com.oobj.service.cliente.ClienteService;
import br.com.oobj.util.builder.ClienteBuilder;

public class ClienteServiceTest {

	@Test
	public void deveSalvarConsultarExcluirUmCliente() {
		Cliente cliente = new ClienteBuilder()
				.comNome("Átilla")
				.comCPF("123.123.123-00")
				.moraNoEndereco("Rua 88, 559, Setor Sul - Goiânia")
				.comTelefone("9357-3934")
				.construir();
		
		// Primeito cria e salva um cliente
		ClienteService service = new ClienteService();
		cliente = service.salvar(cliente);
		assertNotNull(cliente);
		assertNotEquals(new Integer(0), cliente.getId());
		
		// Agora consulta no banco
		cliente = service.consultarPorId(cliente.getId());
		assertNotNull(cliente);
		assertNotEquals(new Integer(0), cliente.getId());

		// Agora exclui
		service.excluir(cliente);
		cliente = service.consultarPorId(cliente.getId());
		assertNull(cliente);
	}
}
