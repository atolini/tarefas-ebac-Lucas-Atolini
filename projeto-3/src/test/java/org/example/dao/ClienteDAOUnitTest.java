package org.example.dao;

import org.example.dao.generic.IGenericDAO;
import org.example.domain.Cliente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class ClienteDAOUnitTest {
    private Cliente c;
    private IGenericDAO<Cliente, Long> genericDAO;

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

    public ClienteDAOUnitTest() {
        genericDAO = new ClienteDAOMock();
    }

    @Test
    public void cadastrarTest() {
        Boolean returnValue = genericDAO.cadastrar(this.c);
        assertTrue(returnValue);
    }

    @Test
    public void excluirTest() {
        Boolean returnValue = genericDAO.excluir(100_100_100_10L);
        assertTrue(returnValue);
    }

    @Test
    public void alterarTest() {
        Boolean returnValue = genericDAO.alterar(this.c);
        assertTrue(returnValue);
    }

    @Test
    public void buscarTest() {
        Cliente returnValue = genericDAO.buscar(10_100_100_10L);
        assertNotNull(returnValue);
    }

    @Test
    public void buscarTodosTest() {
        Collection<Cliente> returnValue = genericDAO.buscarTodos();
    }
}