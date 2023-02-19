package org.example.domain;

import org.example.annotations.ColunaTabela;
import org.example.annotations.Tabela;
import org.example.annotations.TipoChave;
import org.example.dao.generic.Persistence;

import java.math.BigDecimal;

@Tabela("tb_produto")
public class Produto implements Persistence {
    @ColunaTabela(dbName = "id", setJavaName = "setId", getJavaName = "getId")
    private Long id;

    @TipoChave("getCodigo")
    @ColunaTabela(dbName = "codigo", setJavaName = "setCodigo", getJavaName = "getCodigo")
    private String codigo;

    @ColunaTabela(dbName = "nome", setJavaName = "setNome", getJavaName = "getNome")
    private String nome;

    @ColunaTabela(dbName = "descricao", setJavaName = "setDescricao", getJavaName = "getDescricao")
    private String descricao;

    @ColunaTabela(dbName = "valor", setJavaName = "setValor", getJavaName = "getValor")
    private BigDecimal valor;

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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
}
