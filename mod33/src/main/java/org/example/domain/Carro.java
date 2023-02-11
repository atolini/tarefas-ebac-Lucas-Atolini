package org.example.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity(name = "carro")
@Table(name = "tb_carro")
public class Carro extends Persistent {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_carro")
    private Long id;

    private String nome;

    @ManyToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name = "marca_id")
    private Marca marca;

    private Double valor;

    private List<String> cores;

    @ManyToMany(cascade=CascadeType.PERSIST)
    @JoinTable(name = "tb_carro_acessorio",
            joinColumns = @JoinColumn(name = "carro_id"),
            inverseJoinColumns = @JoinColumn(name = "acessorio_id"))
    private List<Acessorio> acessorios;

    public Carro() {}

    public Carro(String nome, Marca marca, Double valor, List<String> cores, List<Acessorio> acessorios) {
        this.nome = nome;
        this.marca = marca;
        this.valor = valor;
        this.cores = cores;
        this.acessorios = acessorios;
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

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public List<String> getCores() {
        return cores;
    }

    public void setCores(List<String> cores) {
        this.cores = cores;
    }

    public List<Acessorio> getAcessorios() {
        return acessorios;
    }

    public void setAcessorios(List<Acessorio> acessorios) {
        this.acessorios = acessorios;
    }
}
