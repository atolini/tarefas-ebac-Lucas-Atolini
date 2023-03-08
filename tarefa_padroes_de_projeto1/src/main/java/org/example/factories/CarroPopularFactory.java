package org.example.factories;

import org.example.domain.Hatch;
import org.example.domain.PopularHatch;
import org.example.domain.PopularSedan;
import org.example.domain.Sedan;

public final class CarroPopularFactory implements CarroFactory {
    @Override
    public Sedan criarSedan() {
        return new PopularSedan();
    }

    @Override
    public Hatch criarHatch() {
        return new PopularHatch();
    }
}
