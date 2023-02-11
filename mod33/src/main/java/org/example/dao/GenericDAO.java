package org.example.dao;

import jakarta.persistence.*;
import org.example.domain.Carro;
import org.example.domain.Persistent;

import java.util.List;

public class GenericDAO implements IGenericDAO {
    @Override
    public <T extends Persistent> T register(T p) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("ExemploJPA");
        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();
        try {
            entityManager.persist(p);
        } catch (Exception e) {
            return null;
        }
        entityManager.getTransaction().commit();
        entityManager.close();
        factory.close();
        return p;
    }

    @Override
    public Carro findCarByName(String name) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("ExemploJPA");
        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();
        String jpql = "select c from carro c join fetch c.marca join fetch c.acessorios where c.nome = :nome";
        TypedQuery<Carro> query = entityManager.createQuery(jpql, Carro.class);
        query.setParameter("nome", name);
        List<Carro> c = query.getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        factory.close();
        return c.get(0);
    }
}
