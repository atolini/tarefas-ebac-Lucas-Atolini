package org.example.dao;

import org.example.dao.generic.IGenericDAO;
import org.example.dao.generic.IVendaDAO;
import org.example.domain.Cliente;
import org.example.domain.Produto;
import org.example.domain.Venda;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.util.Collection;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class VendaDAOTest {
    private IGenericDAO produtoDAO;
    private IGenericDAO clienteDAO;
    private IVendaDAO vendaDAO;
    private Cliente cliente;
    private Produto produto;

    public VendaDAOTest() {
        this.produtoDAO = new ProdutoDAO();
        this.clienteDAO = new ClienteDAO();
        this.vendaDAO = new VendaDAO();
    }

    /* Metodos de ciclo de vida */
    @BeforeEach
    public void init() {
        this.cliente = cadastrarCliente();
        this.produto = cadastrarProduto();
    }

    @AfterEach
    public void end() {
        excluirVendas();
        excluirProdutos();
        this.clienteDAO.excluir(this.cliente);
    }

    /* Testes */
    @Test
    public void pesquisar() {
        Venda v = criarVenda("A1");
        Venda vRetorno = this.vendaDAO.cadastrar(v);
        assertNotNull(vRetorno);

        Venda vBanco = this.vendaDAO.buscar(vRetorno.getId());
        assertNotNull(vBanco);
        assertEquals(v.getCodigo(), vBanco.getCodigo());
    }

    @Test
    public void salvar() {
        Venda v = criarVenda("A2");
        Venda vRetorno = this.vendaDAO.cadastrar(v);
        assertNotNull(vRetorno);

        assertTrue(v.getValorTotal().equals(BigDecimal.valueOf(20)));
        assertTrue(v.getStatus().equals(Venda.Status.INICIADA));

        Venda vBanco = this.vendaDAO.buscar(v.getId());
        assertTrue(vBanco.getId() != null && vBanco.getId() > 0);
        assertEquals(v.getCodigo(), vBanco.getCodigo());
    }

    @Test
    public void cancelarVenda() {
        String cod = "A3";
        Venda v = criarVenda(cod);
        Venda vRetorno = this.vendaDAO.cadastrar(v);
        assertNotNull(vRetorno);
        assertNotNull(v);
        assertEquals(cod, vRetorno.getCodigo());

        vRetorno.setStatus(Venda.Status.CANCELADA);
        this.vendaDAO.cancelarVenda(vRetorno);

        Venda vBanco = this.vendaDAO.buscar(vRetorno.getId());
        assertEquals(cod, vBanco.getCodigo());
        assertEquals(Venda.Status.CANCELADA, vBanco.getStatus());
    }

    @Test
    public void addMaisProdutos() {
        String cod = "A4";
        Venda v = criarVenda(cod);
        Venda vRetorno = this.vendaDAO.cadastrar(v);
        assertNotNull(vRetorno);
        assertNotNull(v);
        assertEquals(cod, vRetorno.getCodigo());

        Venda vBanco = this.vendaDAO.consultarComCollection(vRetorno.getId());
        vBanco.addProduto(this.produto, 2);

        assertEquals(vBanco.getQuantidadeTotalProdutos(), 4);
        BigDecimal valorTotal = BigDecimal.valueOf(40).setScale(2, RoundingMode.HALF_DOWN);
        assertTrue(vBanco.getValorTotal().equals(valorTotal));
        assertTrue(vBanco.getStatus().equals(Venda.Status.INICIADA));
    }

    @Test
    public void addMaisProdutosDiferentes() {
        String cod = "A5";
        Venda v = criarVenda(cod);
        Venda vRetorno = this.vendaDAO.cadastrar(v);
        assertNotNull(vRetorno);
        assertNotNull(v);
        assertEquals(cod, vRetorno.getCodigo());

        Produto p = new Produto();
        p.setCodigo("A5");
        p.setValor(BigDecimal.TWO);
        p.setDescricao("Desc");
        p.setNome("Nome");
        this.produtoDAO.cadastrar(p);

        // Recuperando do banco
        Venda vBanco = this.vendaDAO.consultarComCollection(vRetorno.getId());

        // Alterando e buscando novamente
        vBanco.addProduto(p, 3);
        this.vendaDAO.alterar(vBanco);
        vBanco = this.vendaDAO.consultarComCollection(vRetorno.getId());

        assertTrue(vBanco.getQuantidadeTotalProdutos() == 5);
        BigDecimal valorTotal = BigDecimal.valueOf(26).setScale(2, RoundingMode.HALF_DOWN);
        assertTrue(vBanco.getValorTotal().equals(valorTotal));
        assertTrue(vBanco.getStatus().equals(Venda.Status.INICIADA));
    }

    @Test
    public void salvarVendaComMesmoCod() {
        String cod = "A6";
        Venda v = criarVenda(cod);
        Venda vRetorno1 = this.vendaDAO.cadastrar(v);

        Venda v2 = criarVenda(cod);
        assertThrows(Exception.class, () -> this.vendaDAO.cadastrar(v));
    }

    @Test
    public void removerProduto() {
        String cod = "A8";
        Venda v = criarVenda(cod);
        Venda vRetorno = vendaDAO.cadastrar(v);
        assertNotNull(vRetorno);
        assertEquals(v.getCodigo(), vRetorno.getCodigo());

        Produto p = new Produto();
        p.setCodigo(cod);
        p.setValor(BigDecimal.TEN);
        p.setDescricao("Desc");
        p.setNome("Nome");
        p = (Produto) this.produtoDAO.cadastrar(p);
        assertNotNull(p);
        assertEquals(cod, p.getCodigo());

        Venda vBanco = this.vendaDAO.consultarComCollection(vRetorno.getId());
        vBanco.addProduto(p, 2);
        assertTrue(vBanco.getQuantidadeTotalProdutos() == 4);
        BigDecimal valorTotal = BigDecimal.valueOf(40).setScale(2, RoundingMode.HALF_DOWN);
        assertTrue(vBanco.getValorTotal().equals(valorTotal));

        vBanco.removerProduto(p, 2);
        Venda vBanco2 = this.vendaDAO.alterar(vBanco);
        assertNotNull(vBanco2);

        assertTrue(vBanco2.getQuantidadeTotalProdutos() == 2);
        valorTotal = BigDecimal.valueOf(20).setScale(2, RoundingMode.HALF_DOWN);
        assertTrue(vBanco2.getValorTotal().equals(valorTotal));
        assertTrue(vBanco2.getStatus().equals(Venda.Status.INICIADA));
    }

    @Test
    public void removerTodos() {
        String cod = "A9";
        Venda v = criarVenda(cod);
        Venda vRetorno = vendaDAO.cadastrar(v);
        assertNotNull(vRetorno);
        assertEquals(v.getCodigo(), vRetorno.getCodigo());

        Produto p = new Produto();
        p.setCodigo(cod);
        p.setValor(BigDecimal.TEN);
        p.setDescricao("Desc");
        p.setNome("Nome");
        p = (Produto) this.produtoDAO.cadastrar(p);
        assertNotNull(p);
        assertEquals(cod, p.getCodigo());

        Venda vBanco = this.vendaDAO.consultarComCollection(vRetorno.getId());
        vBanco.addProduto(p, 2);
        assertTrue(vBanco.getQuantidadeTotalProdutos() == 4);
        BigDecimal valorTotal = BigDecimal.valueOf(40).setScale(2, RoundingMode.HALF_DOWN);
        assertTrue(vBanco.getValorTotal().equals(valorTotal));

        vBanco.removerProduto(p, 2);
        vBanco.removerProduto(this.produto, 2);
        Venda vBanco2 = this.vendaDAO.alterar(vBanco);
        assertNotNull(vBanco2);

        assertTrue(vBanco2.getQuantidadeTotalProdutos() == 0);
        assertTrue(vBanco2.getValorTotal().doubleValue() == 0.0);
        assertTrue(vBanco2.getStatus().equals(Venda.Status.INICIADA));
    }

    @Test
    public void finalizarVenda() {
        String cod = "A10";
        Venda v = criarVenda(cod);
        Venda vRetorno = vendaDAO.cadastrar(v);
        assertNotNull(vRetorno);
        assertEquals(v.getCodigo(), vRetorno.getCodigo());

        vRetorno.setStatus(Venda.Status.CONCLUIDA);
        this.vendaDAO.finalizarVenda(vRetorno);

        Venda vBanco = this.vendaDAO.consultarComCollection(vRetorno.getId());
        assertEquals(vRetorno.getCodigo(), vBanco.getCodigo());
        assertEquals(Venda.Status.CONCLUIDA, vBanco.getStatus());
    }

    @Test
    public void tentarAddProdVendaConcluida() {
        String cod = "A11";
        Venda v = criarVenda(cod);
        Venda vRetorno = vendaDAO.cadastrar(v);
        assertNotNull(vRetorno);
        assertEquals(v.getCodigo(), vRetorno.getCodigo());

        vRetorno.setStatus(Venda.Status.CONCLUIDA);
        this.vendaDAO.finalizarVenda(vRetorno);

        Venda vBanco = this.vendaDAO.consultarComCollection(vRetorno.getId());
        assertEquals(vRetorno.getCodigo(), vBanco.getCodigo());
        assertEquals(Venda.Status.CONCLUIDA, vBanco.getStatus());

        assertThrows(UnsupportedOperationException.class, () -> vBanco.addProduto(this.produto, 2));
    }

    /* Metodos de apoio */
    private Cliente cadastrarCliente() {
        Cliente c = new Cliente();
        c.setCpf(new Random().nextLong());
        c.setNome("Lucas");
        c.setCidade("Barueri");
        c.setEnd("End");
        c.setEstado("SP");
        c.setNumero(7);
        c.setTel(1190000_0000L);
        return (Cliente) this.clienteDAO.cadastrar(c);
    }

    private Produto cadastrarProduto() {
        Produto p = new Produto();
        p.setCodigo("A1");
        p.setValor(BigDecimal.TEN);
        p.setDescricao("Desc");
        p.setNome("Nome");
        return (Produto) this.produtoDAO.cadastrar(p);
    }

    private Venda criarVenda(String cod) {
        Venda v = new Venda();
        v.setCodigo(cod);
        v.setDataVenda(Instant.now());
        v.setCliente(this.cliente);
        v.setStatus(Venda.Status.INICIADA);
        v.addProduto(this.produto, 2);
        return v;
    }

    private void excluirVendas() {
        Collection<Venda> lista = this.vendaDAO.buscarTodos();
        lista.forEach(v -> {
            try {
                this.vendaDAO.excluir(v);
            } catch (Exception e){
                e.printStackTrace();
            }
        });
    }

    private void excluirProdutos() {
        Collection<Produto> lista = this.produtoDAO.buscarTodos();
        lista.forEach(p -> {
            try {
                this.produtoDAO.excluir(p);
            } catch (Exception e){
                e.printStackTrace();
            }
        });
    }
}