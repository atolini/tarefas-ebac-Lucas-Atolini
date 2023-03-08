package org.example.domain;

import jakarta.persistence.*;
import org.example.dao.generic.Persistence;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Entity
@Table(name = "tb_venda")
public final class Venda implements Persistence {

    /* Enum */
    public enum Status {
        INICIADA, CONCLUIDA, CANCELADA;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "venda_seq")
    @SequenceGenerator(name = "venda_seq", sequenceName = "sq_venda", initialValue = 1, allocationSize = 1)
    private Long id;

    @Column(name = "codigo", nullable = false, unique = true)
    private String codigo;

    @ManyToOne
    @JoinColumn(name = "id_cliente_fk",
            foreignKey = @ForeignKey(name = "fk_venda_cliente"),
            referencedColumnName = "id",
            nullable = false)
    private Cliente cliente;

    @OneToMany(mappedBy = "venda", cascade = CascadeType.ALL)
    private Set<ProdutoQuantidade> produtos;

    @Column(name = "valor_total", nullable = false)
    private BigDecimal valorTotal;

    @Column(name = "data_venda", nullable = false)
    private Instant dataVenda;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_venda", nullable = false)
    private Status status;

    public Venda() {
        this.produtos = new HashSet<>();
    }

    // Metodos
    public void addProduto(Produto p, int quantidade) {
        validarStatus();
        Optional<ProdutoQuantidade> optional = this.produtos.stream().filter(f -> {
            return f.getProduto().getCodigo().equals(p.getCodigo());
        }).findAny();

        if (optional.isPresent()) {
            ProdutoQuantidade prodQuantidade = optional.get();
            prodQuantidade.adicionar(quantidade);
        } else {
            ProdutoQuantidade prodQuantidade = new ProdutoQuantidade();
            prodQuantidade.setProduto(p);
            prodQuantidade.adicionar(quantidade);
            prodQuantidade.setVenda(this);
            this.produtos.add(prodQuantidade);
        }
        this.recalcularValor();
    }

    public int getQuantidadeTotalProdutos() {
        int res = 0;
        for (ProdutoQuantidade p : this.produtos) {
            res += p.getQuantidade();
        }
        return res;
    }

    public void removerProduto(Produto produto, int quantidade) {
        validarStatus();
        Optional<ProdutoQuantidade> optional =
                this.produtos.stream().filter(f -> f.getProduto().getCodigo().equals(produto.getCodigo())).findAny();

        if (optional.isPresent()) {
            ProdutoQuantidade produtoQuantidade = optional.get();
            if (produtoQuantidade.getQuantidade() >= quantidade) {
                produtoQuantidade.remover(quantidade);
            }
        } else {
            this.produtos.remove(optional.get());
        }

        recalcularValor();
    }

    // Metodos de apoio
    private void validarStatus() {
        if (this.status == Status.CONCLUIDA) {
            throw new UnsupportedOperationException("Impossível alterar venda realizada!");
        }
    }

    private void recalcularValor() {
        BigDecimal valorTotal = BigDecimal.ZERO;
        for (ProdutoQuantidade p : this.produtos) {
            valorTotal = valorTotal.add(p.getValorTotal());
        }
        this.valorTotal = valorTotal;
    }

    // Getters e setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Set<ProdutoQuantidade> getProdutos() {
        return produtos;
    }

    public void setProdutos(Set<ProdutoQuantidade> produtos) {
        this.produtos = produtos;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Instant getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(Instant dataVenda) {
        this.dataVenda = dataVenda;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
