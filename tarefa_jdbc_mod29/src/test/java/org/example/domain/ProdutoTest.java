package org.example.domain;

import org.example.jdbc.DAO.IProdutoDAO;
import org.example.jdbc.DAO.ProdutoDAO;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ProdutoTest {
    @Test
    public void cadastrarProduto() throws Exception {
        IProdutoDAO produtoDAO = new ProdutoDAO();

        // Instanciando
        Produto pEnviado = new Produto();
        pEnviado.setNome("Arroz");
        pEnviado.setCodigo("5");
        pEnviado.setFabricante("Camil");

        // Enviando registro
        Integer count = produtoDAO.cadastrar(pEnviado);
        assertTrue(count > 0);

        // Testando registro
        Produto pRetornado = produtoDAO.buscar("5");
        assertNotNull(pRetornado);
        assertEquals(pRetornado.getCodigo(), pEnviado.getCodigo());
        assertEquals(pRetornado.getNome(), pEnviado.getNome());
        assertEquals(pRetornado.getFabricante(), pEnviado.getFabricante());

        Integer countDel = produtoDAO.excluir(pRetornado);
        assertTrue(countDel > 0);
    }

    @Test
    public void buscarTest() throws Exception {
        IProdutoDAO produtoDAO = new ProdutoDAO();

        // Enviando registro
        Produto pEnviado = new Produto();
        pEnviado.setNome("Arroz");
        pEnviado.setCodigo("5");
        pEnviado.setFabricante("Camil");
        Integer countCad = produtoDAO.cadastrar(pEnviado);
        assertTrue(countCad > 0);

        // Buscando registro
        Produto pRetorno = produtoDAO.buscar("5");

        // Testando
        assertNotNull(pRetorno);
        assertEquals(pEnviado.getCodigo(), pRetorno.getCodigo());
        assertEquals(pEnviado.getNome(), pRetorno.getNome());
        assertEquals(pEnviado.getFabricante(), pRetorno.getFabricante());

        // Deletando
        produtoDAO.excluir(pRetorno);
    }

    @Test
    public void buscarTodosProdutosTest() throws Exception {
        IProdutoDAO produtoDAO = new ProdutoDAO();

        // Atomic Integer
        AtomicInteger atomic = new AtomicInteger(0);

        // Criando registros 10 aleatorios
        List<Produto> listaCriada = new ArrayList<>(Stream.generate(atomic::incrementAndGet)
                .limit(10)
                .map((atomicNumber) -> {
                    Produto p = new Produto();
                    p.setCodigo(atomicNumber.toString());
                    p.setNome("Nome" + atomicNumber);
                    p.setFabricante("Fabricante" + atomicNumber);
                    return p;
                }).toList());

        // Enviando ao banco
        listaCriada.forEach((p) -> {
            try {
                produtoDAO.cadastrar(p);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        // Buscando do banco
        List<Produto> listaRetorno = produtoDAO.buscarTodos();

        // Testando
        listaRetorno.forEach((p) -> {
            assertTrue(listaCriada.contains(p));
            assertTrue(listaCriada.remove(p));
        });

        // Deletando do banco
        listaRetorno.forEach((p) -> {
            try {
                produtoDAO.excluir(p);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Test
    public void atualizarTest() throws Exception {
        IProdutoDAO produtoDAO = new ProdutoDAO();

        // Criando e inserindo um novo registro
        Produto pEnviado = new Produto();
        pEnviado.setCodigo("5");
        pEnviado.setNome("Arroz");
        pEnviado.setFabricante("Camil");
        Integer countCad = produtoDAO.cadastrar(pEnviado);
        assertTrue(countCad > 0);

        // Buscando o registro e testando
        Produto pRetornado = produtoDAO.buscar("5");
        assertNotNull(pRetornado);
        assertEquals(pEnviado.getCodigo(), pRetornado.getCodigo());
        assertEquals(pEnviado.getNome(), pRetornado.getNome());
        assertEquals(pEnviado.getFabricante(), pRetornado.getFabricante());

        // Atualizando
        pRetornado.setCodigo("10");
        pRetornado.setNome("Feijao");
        pRetornado.setFabricante("Aurora");
        Integer countUpdate = produtoDAO.atualizar(pRetornado);
        assertTrue(countUpdate > 0);

        // Buscando o antigo registro
        Produto pNull = produtoDAO.buscar("5");
        assertNull(pNull);

        // Testando update
        pRetornado = produtoDAO.buscar("10");
        assertNotNull(pRetornado);
        assertEquals("10", pRetornado.getCodigo());
        assertEquals("Feijao", pRetornado.getNome());
        assertEquals("Aurora", pRetornado.getFabricante());

        // Deletando
        List<Produto> todos = produtoDAO.buscarTodos();
        for (Produto p : todos) {
            produtoDAO.excluir(p);
        }
    }

    @Test
    public void excluirTest() throws Exception {
        IProdutoDAO produtoDAO = new ProdutoDAO();

        // Cadastrando
        Produto p = new Produto();
        p.setCodigo("5");
        p.setNome("Arroz");
        p.setFabricante("Camil");
        Integer countCad = produtoDAO.cadastrar(p);
        assertTrue(countCad > 0);

        // Testando cadastro
        Produto pRetornado = produtoDAO.buscar("5");
        assertNotNull(pRetornado);
        assertEquals(p.getCodigo(), pRetornado.getCodigo());
        assertEquals(p.getNome(), pRetornado.getNome());
        assertEquals(p.getFabricante(), pRetornado.getFabricante());

        // Deletando
        Integer countDel = produtoDAO.excluir(pRetornado);
        assertTrue(countDel > 0);

        // Testando delete
        Produto pRetornado2 = produtoDAO.buscar("5");
        assertNull(pRetornado2);
    }
}