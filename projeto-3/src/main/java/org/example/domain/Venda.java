package org.example.domain;

import org.example.annotations.ColunaTabela;
import org.example.annotations.Tabela;
import org.example.dao.generic.Persistence;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Tabela("tb_venda")
public class Venda implements Persistence {

    public enum Status {
        INICIADA, CONCLUIDA, CANCELADA
    }

    @ColunaTabela(dbName = "id", setJavaName = "setId", getJavaName = "getId")
    private Long id;

    @ColunaTabela(dbName = "codigo", setJavaName = "setCodigo", getJavaName = "getCodigo")
    private String codigo;

    //@ColunaTabela(dbName = "id_cliente_fk", setJavaName = "setIdClienteFk")
    private Cliente cliente;

    // @ColunaTabela(dbName = "", setJavaName = "")
    private Set<ProdutoQuantidade> produtos;

    @ColunaTabela(dbName = "valor_total", setJavaName = "setValorTotal", getJavaName = "getValorTotal")
    private BigDecimal valorTotal;

    @ColunaTabela(dbName = "data_venda", setJavaName = "setDataVenda", getJavaName = "getDataVenda")
    private Instant dataVenda;

    @ColunaTabela(dbName = "status_venda", setJavaName = "setStatus", getJavaName = "getStatus")
    private Status status;

    public Venda() {
        this.produtos = new HashSet<>();
    }

    // Getters e setters
    @Override
    public Long getId() {
        return id;
    }

    @Override
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
