package org.example.domain;

import org.example.jdbc.DAO.ClienteDAO;
import org.example.jdbc.DAO.IClienteDAO;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ClienteTest {
    @Test
    public void cadastrarTest() throws Exception {
        IClienteDAO clienteDAO = new ClienteDAO();

        // Instanciando
        Cliente clienteEnviado = new Cliente();
        clienteEnviado.setNome("Lucas");
        clienteEnviado.setCodigo("5");

        // Enviando registro
        Integer count = clienteDAO.cadastrar(clienteEnviado);
        assertTrue(count > 0);

        // Testando registro
        Cliente clienteRetornado = clienteDAO.buscar("5");
        assertNotNull(clienteRetornado);
        assertEquals(clienteEnviado.getCodigo(), clienteRetornado.getCodigo());
        assertEquals(clienteEnviado.getNome(), clienteRetornado.getNome());

        Integer countDel = clienteDAO.excluir(clienteRetornado);
        assertTrue(countDel > 0);
    }

    @Test
    public void buscarTest() throws Exception {
        IClienteDAO clienteDAO = new ClienteDAO();

        // Enviando registro
        Cliente clienteEnviado = new Cliente();
        clienteEnviado.setNome("Lucas");
        clienteEnviado.setCodigo("5");
        Integer countCad = clienteDAO.cadastrar(clienteEnviado);
        assertTrue(countCad > 0);

        // Buscando registro
        Cliente clienteRetorno = clienteDAO.buscar("5");

        // Testando
        assertNotNull(clienteRetorno);
        assertEquals(clienteEnviado.getCodigo(), clienteRetorno.getCodigo());
        assertEquals(clienteEnviado.getNome(), clienteRetorno.getNome());

        // Deletando
        clienteDAO.excluir(clienteRetorno);
    }

    @Test
    public void buscarTodosClientesTest() throws Exception {
        IClienteDAO clienteDAO = new ClienteDAO();

        // Atomic Integer
        AtomicInteger atomic = new AtomicInteger(0);

        // Criando registros 10 aleatorios
        List<Cliente> listaCriada = new ArrayList<>(Stream.generate(atomic::incrementAndGet)
                .limit(10)
                .map((atomicNumber) -> {
                    String nome = "Nome" + atomicNumber;
                    Cliente c = new Cliente();
                    c.setCodigo(atomicNumber.toString());
                    c.setNome(nome);
                    return c;
                }).toList());

        // Enviando ao banco
        listaCriada.forEach((c) -> {
            try {
                clienteDAO.cadastrar(c);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        // Buscando do banco
        List<Cliente> listaRetorno = clienteDAO.buscarTodos();

        // Testando
        listaRetorno.forEach((c) -> {
            assertTrue(listaCriada.contains(c));
            assertTrue(listaCriada.remove(c));
        });

        // Deletando do banco
        listaRetorno.forEach((c) -> {
            try {
                clienteDAO.excluir(c);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Test
    public void atualizarTest() throws Exception {
        IClienteDAO clienteDAO = new ClienteDAO();

        // Criando e inserindo um novo registro
        Cliente clienteEnviado = new Cliente();
        clienteEnviado.setCodigo("5");
        clienteEnviado.setNome("Lucas");
        Integer countCad = clienteDAO.cadastrar(clienteEnviado);
        assertTrue(countCad > 0);

        // Buscando o registro e testando
        Cliente clienteRetornado = clienteDAO.buscar("5");
        assertNotNull(clienteRetornado);
        assertEquals(clienteEnviado.getCodigo(), clienteRetornado.getCodigo());
        assertEquals(clienteEnviado.getNome(), clienteRetornado.getNome());

        // Atualizando
        clienteRetornado.setCodigo("10");
        clienteRetornado.setNome("Gabriela");
        Integer countUpdate = clienteDAO.atualizar(clienteRetornado);
        assertTrue(countUpdate > 0);

        // Buscando o antigo registro
        Cliente clienteNull = clienteDAO.buscar("5");
        assertNull(clienteNull);

        // Testando update
        clienteRetornado = clienteDAO.buscar("10");
        assertNotNull(clienteRetornado);
        assertEquals("10", clienteRetornado.getCodigo());
        assertEquals("Gabriela", clienteRetornado.getNome());

        // Deletando
        List<Cliente> todos = clienteDAO.buscarTodos();
        for (Cliente c : todos) {
            clienteDAO.excluir(c);
        }
    }

    @Test
    public void excluirTest() throws Exception {
        IClienteDAO clienteDAO = new ClienteDAO();

        // Cadastrando
        Cliente c = new Cliente();
        c.setCodigo("5");
        c.setNome("Lucas");
        Integer countCad = clienteDAO.cadastrar(c);
        assertTrue(countCad > 0);

        // Testando cadastro
        Cliente cRetornado = clienteDAO.buscar("5");
        assertNotNull(cRetornado);
        assertEquals(c.getCodigo(), cRetornado.getCodigo());
        assertEquals(c.getNome(), cRetornado.getNome());

        // Deletando
        Integer countDel = clienteDAO.excluir(cRetornado);
        assertTrue(countDel > 0);

        // Testando delete
        Cliente cRetornado2 = clienteDAO.buscar("5");
        assertNull(cRetornado2);
    }
}