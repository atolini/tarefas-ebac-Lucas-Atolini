package org.example.domain;

import jakarta.persistence.*;
import org.example.dao.generic.Persistence;

import java.math.BigDecimal;

@Entity
@Table(name = "tb_produto_quantidade")
public final class ProdutoQuantidade implements Persistence {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "prod_qtd_seq")
    @SequenceGenerator(name = "prod_qtd_seq", sequenceName = "sq_prod_qtd", initialValue = 1, allocationSize = 1)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    private Produto produto;

    @Column(name = "quantidade", nullable = false)
    private Integer quantidade;

    @Column(name = "valor_total", nullable = false)
    private BigDecimal valorTotal;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_venda_fk",
            foreignKey = @ForeignKey(name = "fk_prod_qtd_venda"),
            referencedColumnName = "id",
            nullable = false)
    private Venda venda;

    public ProdutoQuantidade() {
        this.quantidade = 0;
        this.valorTotal = BigDecimal.ZERO;
    }

    // Metodos
    public void adicionar(int quantidade) {
        this.quantidade += quantidade;
        BigDecimal novoValor = this.produto.getValor().multiply(BigDecimal.valueOf(quantidade));
        BigDecimal novoTotal = this.valorTotal.add(novoValor);
        this.valorTotal = novoTotal;
    }

    public void remover(int quantidade) {
        this.quantidade -= quantidade;
        BigDecimal temp = this.produto.getValor().multiply(BigDecimal.valueOf(quantidade));
        this.valorTotal = this.valorTotal.subtract(temp);
    }

    // Getters e setters
    public Long getId() {
        return id;
    }

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

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }
}
