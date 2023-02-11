package org.example.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_cliente")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_cliente")
    private Integer id;

    @Column(nullable = false, length = 50, name = "nome")
    private String nome;

    @Column(nullable = false, length = 11, name = "cpf")
    private Long cpf;

    public Cliente() {}

    public Cliente(String nome, Long cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }

    public Cliente(Integer id, String nome, Long cpf) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
    }

    // Getters e setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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
}
