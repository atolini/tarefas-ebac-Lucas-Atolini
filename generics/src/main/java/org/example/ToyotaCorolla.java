package org.example;

public final class ToyotaCorolla extends Carro {
    public ToyotaCorolla(String placa, String cor, String fabricante) {
        super(placa, cor, fabricante);
    }

    @Override
    public String toString() {
        return "Corolla";
    }
}
