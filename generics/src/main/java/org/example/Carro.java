package org.example;

public abstract class Carro {
    private String placa;
    private String cor;
    private String fabricante;

    public Carro(String placa, String cor, String fabricante) {
        this.placa = placa;
        this.cor = cor;
        this.fabricante = fabricante;
    }

    public String getPlaca() {
        return placa;
    }

    public String getCor() {
        return cor;
    }

    public String getFabricante() {
        return fabricante;
    }
}
