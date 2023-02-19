package org.example.domain;

import org.example.annotations.ColunaTabela;
import org.example.annotations.Tabela;
import org.example.dao.generic.Persistence;
import org.example.annotations.TipoChave;

@Tabela("tb_cliente")
public class Cliente implements Persistence {
    @ColunaTabela(dbName = "id", setJavaName = "setId", getJavaName = "getId")
    private Long id;

    @ColunaTabela(dbName = "nome", setJavaName = "setNome", getJavaName = "getNome")
    private String nome;

    @TipoChave("getCpf")
    @ColunaTabela(dbName = "cpf", setJavaName = "setCpf", getJavaName = "getCpf")
    private Long cpf;

    @ColunaTabela(dbName = "tel", setJavaName = "setTel", getJavaName = "getTel")
    private Long tel;

    @ColunaTabela(dbName = "endereco", setJavaName = "setEnd", getJavaName = "getEnd")
    private String end;

    @ColunaTabela(dbName = "numero", setJavaName = "setNumero", getJavaName = "getNumero")
    private Integer numero;

    @ColunaTabela(dbName = "cidade", setJavaName = "setCidade", getJavaName = "getCidade")
    private String cidade;

    @ColunaTabela(dbName = "estado", setJavaName = "setEstado", getJavaName = "getEstado")
    private String estado;

    // Getters e setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getCpf() {
        return cpf;
    }

    public void setCpf(Long cpf) {
        this.cpf = cpf;
    }

    public Long getTel() {
        return tel;
    }

    public void setTel(Long tel) {
        this.tel = tel;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cliente cliente = (Cliente) o;

        return cpf.equals(cliente.cpf);
    }

    @Override
    public int hashCode() {
        return cpf.hashCode();
    }
}
