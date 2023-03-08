package org.example;

public final class FiatPalio extends Carro {
    public FiatPalio(String placa, String cor, String fabricante) {
        super(placa, cor, fabricante);
    }

    @Override
    public String toString() {
        return "Palio";
    }
}
