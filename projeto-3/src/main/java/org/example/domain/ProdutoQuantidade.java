package org.example.domain;

import org.example.annotations.ColunaTabela;
import org.example.annotations.Tabela;
import org.example.dao.generic.Persistence;

import java.math.BigDecimal;

@Tabela("tb_produto_quantidade")
public class ProdutoQuantidade implements Persistence {
    @ColunaTabela(dbName = "id", setJavaName = "setId", getJavaName = "getId")
    private Long id;

    // @ColunaTabela(dbName = "id", setJavaName = "setId")
    private Produto produto;

    @ColunaTabela(dbName = "quantidade", setJavaName = "setQuantidade", getJavaName = "getQuantidade")
    private Integer quantidade;

    @ColunaTabela(dbName = "valor_total", setJavaName = "setValorTotal", getJavaName = "getValorTotal")
    private BigDecimal valorTotal;

    public ProdutoQuantidade() {
        this.quantidade = 0;
        this.valorTotal = BigDecimal.ZERO;
    }

    // Metodos para adicionar e remover produtos
    public void adicionar(Integer quantidade) {
        this.quantidade += quantidade;
        BigDecimal novoValor = this.produto.getValor().multiply(BigDecimal.valueOf(quantidade));
        this.valorTotal = this.valorTotal.add(novoValor);
    }

    public void remover(Integer quantidade) {
        this.quantidade -= quantidade;
        BigDecimal novoValor = this.produto.getValor().multiply(BigDecimal.valueOf(quantidade));
        this.valorTotal = this.valorTotal.subtract(novoValor);
    }

    // Getters e Setters
    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }
}
