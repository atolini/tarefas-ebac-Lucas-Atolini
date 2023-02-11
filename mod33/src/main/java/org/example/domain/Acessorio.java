package org.example.domain;

import jakarta.persistence.*;

@Entity(name = "acessorio")
@Table(name = "tb_acessorio")
public class Acessorio extends Persistent {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_acessorio")
    private Long id;

    private String nome;

    private String descricao;

    public Acessorio() {}

    public Acessorio(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    // Getters e setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    // String
    @Override
    public String toString() {
        return "Acessorio{" +
                "nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}
