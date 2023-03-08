package org.example.dao;

import org.example.domain.Carro;
import org.example.domain.Persistent;

public interface IGenericDAO {
    public <T extends Persistent> T register(T p);
    public Carro findCarByName(String name);
}
