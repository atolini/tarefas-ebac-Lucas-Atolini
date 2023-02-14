package org.example.dao;

import org.example.dao.generic.IGenericDAO;
import org.example.domain.Cliente;
import org.example.services.generic.ClienteService2;
import org.example.services.generic.IGenericService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ClienteServiceIntegrationTest {
    private final IGenericService<Cliente, Long> service;
    private Cliente c;

    public ClienteServiceIntegrationTest() {
        IGenericDAO<Cliente, Long> clienteDAO = new ClienteDAO();
        service = new ClienteService2(clienteDAO);
    }

    @BeforeEach
    public void criarCliente() {
        c = new Cliente();
        c.setCpf(100_100_100_10L);
        c.setNome("Lucas");
        c.setTel(11_90000_2222L);
        c.setEnd("Rua Bronze");
        c.setNumero(383);
        c.setEstado("Sp");
        c.setCidade("Barueri");
    }

    @AfterEach
    public void deletar() {
        Cliente resultSearch = service.buscar(c.getCpf());
        if (resultSearch != null) {
            service.excluir(c.getCpf());
        }
    }

    @Test
    public void cadastrarTest() {
        // Cadastro
        Boolean returnValue = service.cadastrar(c);
        assertTrue(returnValue);

        // Recuperação
        Cliente resultSearch = service.buscar(c.getCpf());
        assertNotNull(resultSearch);
        assertEquals(resultSearch, this.c);
    }

    @Test
    public void excluirTest() {
        // Cadastro
        Boolean returnValue = service.cadastrar(c);
        assertTrue(returnValue);

        // Excluindo
        returnValue = service.excluir(c.getCpf());
        assertTrue(returnValue);

        // Recuperando, deve ser null;
        Cliente resultSearch = service.buscar(c.getCpf());
        assertNull(resultSearch);
    }

    @Test
    public void alterarTest() {
        // Cadastro
        Boolean returnValue = service.cadastrar(c);
        assertTrue(returnValue);

        // Alterando
        c.setNome("Gabriela");
        returnValue = service.alterar(this.c);
        assertTrue(returnValue);

        // Recuperando
        Cliente resultSearch = service.buscar(c.getCpf());
        assertEquals(resultSearch.getNome(), this.c.getNome());
    }

    @Test
    public void buscarTest() {
        // Cadastro
        Boolean returnValue = service.cadastrar(c);
        assertTrue(returnValue);

        // Recuperando
        Cliente resultSearch = service.buscar(c.getCpf());
        assertEquals(resultSearch, this.c);
    }
}
