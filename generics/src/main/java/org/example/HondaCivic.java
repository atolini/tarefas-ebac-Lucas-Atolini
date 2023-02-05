package org.example;

public final class HondaCivic extends Carro {
    public HondaCivic(String placa, String cor, String fabricante) {
        super(placa, cor, fabricante);
    }

    @Override
    public String toString() {
        return "Civic";
    }
}
