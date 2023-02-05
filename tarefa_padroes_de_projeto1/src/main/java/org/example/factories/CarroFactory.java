package org.example.factories;

import org.example.domain.Hatch;
import org.example.domain.Sedan;

public interface CarroFactory {
    Sedan criarSedan();
    Hatch criarHatch();
}
