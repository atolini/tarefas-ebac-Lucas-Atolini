package org.example.factories;

import org.example.domain.Hatch;
import org.example.domain.LuxoHatch;
import org.example.domain.LuxoSedan;
import org.example.domain.Sedan;

public final class CarroLuxoFactory implements CarroFactory {
    @Override
    public Sedan criarSedan() {
        return new LuxoSedan();
    }

    @Override
    public Hatch criarHatch() {
        return new LuxoHatch();
    }
}
