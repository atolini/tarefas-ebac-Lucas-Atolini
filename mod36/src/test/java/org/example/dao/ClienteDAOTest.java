package org.example.dao;

import org.example.dao.generic.GenericDAO;
import org.example.domain.Cliente;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class ClienteDAOTest {
    private GenericDAO clienteDAO;

    public ClienteDAOTest() {
        this.clienteDAO = new ClienteDAO();
    }

    @AfterEach
    public void end() {
        Collection<Cliente> todos = clienteDAO.buscarTodos();
        todos.forEach(c -> {
            try {
                clienteDAO.excluir(c);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @Test
    public void pesquisarCliente() {
        Cliente c = gerarCliente();
        clienteDAO.cadastrar(c);

        Cliente cBanco = (Cliente) clienteDAO.buscar(c.getId());
        assertNotNull(cBanco);
    }

    @Test
    public void salvarCliente() {
        Cliente c = gerarCliente();
        Cliente retornoOperSalvar = (Cliente) clienteDAO.cadastrar(c);
        assertNotNull(retornoOperSalvar);

        Cliente retornoConsulta = (Cliente) clienteDAO.buscar(retornoOperSalvar.getId());
        assertNotNull(retornoConsulta);

        clienteDAO.excluir(c);

        retornoConsulta = (Cliente) clienteDAO.buscar(retornoOperSalvar.getId());
        assertNull(retornoConsulta);
    }

    @Test
    public void excluirCliente() {
        Cliente c = gerarCliente();
        Cliente retornoOperSalvar = (Cliente) clienteDAO.cadastrar(c);
        assertNotNull(retornoOperSalvar);

        Cliente retornoConsulta = (Cliente) clienteDAO.buscar(retornoOperSalvar.getId());
        assertNotNull(retornoConsulta);

        clienteDAO.excluir(c);

        retornoConsulta = (Cliente) clienteDAO.buscar(retornoOperSalvar.getId());
        assertNull(retornoConsulta);
    }

    @Test
    public void alterarCliente() {
        Cliente c = gerarCliente();
        Cliente retornoOperSalvar = (Cliente) clienteDAO.cadastrar(c);
        assertNotNull(retornoOperSalvar);

        Cliente retornoConsulta = (Cliente) clienteDAO.buscar(retornoOperSalvar.getId());
        assertNotNull(retornoConsulta);

        retornoConsulta.setNome("Gabriela");
        clienteDAO.alterar(retornoConsulta);

        Cliente cAlterado = (Cliente) clienteDAO.buscar(retornoConsulta.getId());
        assertNotNull(cAlterado);
        assertEquals("Gabriela", cAlterado.getNome());

        clienteDAO.excluir(c);
        retornoConsulta = (Cliente) clienteDAO.buscar(cAlterado.getId());
        assertNull(retornoConsulta);
    }

    @Test
    public void buscarTodos() {
        for (int i = 1; i <= 10; i++) {
            Cliente c = gerarCliente();
            Cliente retornoOperSalvar = (Cliente) clienteDAO.cadastrar(c);
            assertNotNull(retornoOperSalvar);
        }

        Collection<Cliente> lista = clienteDAO.buscarTodos();
        assertNotNull(lista);
        assertEquals(lista.size(), 10);
        assertFalse(lista.isEmpty());

        lista.forEach(c -> {
            try {
                clienteDAO.excluir(c);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        lista = clienteDAO.buscarTodos();
        assertNotNull(lista);
        assertEquals(lista.size(), 0);
        assertTrue(lista.isEmpty());
    }

    /* Metodos de apoio */
    private Cliente gerarCliente() {
        Cliente c = new Cliente();
        c.setCpf(new Random().nextLong());
        c.setNome("Lucas");
        c.setCidade("Barueri");
        c.setEnd("End");
        c.setEstado("SP");
        c.setNumero(7);
        c.setTel(1190000_0000L);
        return c;
    }
}