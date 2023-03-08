package org.example.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity(name = "marca")
@Table(name = "tb_marca")
public class Marca extends Persistent {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_marca")
    private Long id;

    private String nome;

    @OneToMany(mappedBy = "marca")
    private List<Carro> carros;

    public Marca() {}

    public Marca(String nome, List<Carro> carros) {
        this.nome = nome;
        this.carros = carros;
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

    public List<Carro> getCarros() {
        return carros;
    }

    public void setCarros(List<Carro> carros) {
        this.carros = carros;
    }

    @Override
    public String toString() {
        return "Marca{" +
                "nome='" + nome + '\'' +
                '}';
    }
}
