package org.example.domain;

public class Cliente {
    private Long id;
    private String codigo;
    private String nome;

    public Long getId() {
        return id;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cliente cliente = (Cliente) o;

        if (!codigo.equals(cliente.codigo)) return false;
        return nome.equals(cliente.nome);
    }

    @Override
    public int hashCode() {
        int result = codigo.hashCode();
        result = 31 * result + nome.hashCode();
        return result;
    }
}
