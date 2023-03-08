package org.example.dao;

import org.example.domain.Produto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

public class ProdutoDAOTest {
    private ProdutoDAO produtoDAO;

    public ProdutoDAOTest() {
        this.produtoDAO = new ProdutoDAO();
    }

    @AfterEach
    public void end() {
        Collection<Produto> lista = this.produtoDAO.buscarTodos();
        lista.forEach(c -> {
            try {
                this.produtoDAO.excluir(c);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    // Testes
    @Test
    public void salvar() {
        Produto p = criarProduto("A2");
        assertNotNull(p);
        assertNotNull(p.getId());
    }

    @Test
    public void buscar() {
        Produto p = criarProduto("A1");
        assertNotNull(p);
        Produto pBanco = this.produtoDAO.buscar(p.getId());
        assertNotNull(p);
    }

    @Test
    public void excluir() {
        Produto p = criarProduto("A3");
        assertNotNull(p);
        this.produtoDAO.excluir(p);
        Produto pRetorno = this.produtoDAO.buscar(p.getId());
        assertNull(pRetorno);
    }

    @Test
    public void alterar() {
        Produto p = criarProduto("A4");
        p.setNome("Lucas");
        this.produtoDAO.alterar(p);
        Produto pRetorno = this.produtoDAO.buscar(p.getId());
        assertNotNull(pRetorno);
        assertEquals("Lucas", pRetorno.getNome());
    }

    @Test
    public void buscarTodos() {
        criarProduto("A5");
        criarProduto("A6");
        Collection<Produto> lista = this.produtoDAO.buscarTodos();
        assertNotNull(lista);
        assertEquals(lista.size(), 2);

        for (Produto p : lista) {
            this.produtoDAO.excluir(p);
        }

        lista = this.produtoDAO.buscarTodos();
        assertNotNull(lista);
        assertEquals(lista.size(), 0);

    }
    // Metodos de apoio
    public Produto criarProduto(String cod) {
        Produto p = new Produto();
        p.setCodigo(cod);
        p.setDescricao("Produto 1");
        p.setNome("Produto 1");
        p.setValor(BigDecimal.TEN);
        this.produtoDAO.cadastrar(p);
        return p;
    }
}
