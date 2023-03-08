package org.example.dao.generic;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.example.domain.Cliente;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public abstract class GenericDAO<T extends Persistence, E extends Serializable> implements IGenericDAO<T, E> {

    protected EntityManagerFactory entityManagerFactory;

    protected EntityManager entityManager;

    protected Class<T> persistenceClass;

    protected GenericDAO(Class<T> persistenceClass) {
        this.persistenceClass = persistenceClass;
    }

    @Override
    public T cadastrar(T entity) {
        openConnection();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
        closeConnection();
        return entity;
    }

    @Override
    public void excluir(T entity) {
        openConnection();
        entity = entityManager.merge(entity);
        entityManager.remove(entity);
        entityManager.getTransaction().commit();
        closeConnection();
    }

    @Override
    public T buscar(E value) {
        openConnection();
        T entity = (T) entityManager.find(this.persistenceClass, value);
        entityManager.getTransaction().commit();
        closeConnection();
        return entity;
    }

    @Override
    public Collection<T> buscarTodos() {
        openConnection();
        String sb = "SELECT obj FROM " +
                this.persistenceClass.getSimpleName() +
                " obj";
        List<T> list = (List<T>) entityManager.createQuery(sb, this.persistenceClass).getResultList();
        closeConnection();
        return list;
    }

    @Override
    public T alterar(T entity) {
        openConnection();
        entity = entityManager.merge(entity);
        entityManager.getTransaction().commit();
        closeConnection();
        return entity;
    }

    // Open e Close connection;
    protected void closeConnection() {
        entityManager.close();
        entityManagerFactory.close();
    }

    protected void openConnection() {
        this.entityManagerFactory = jakarta.persistence.Persistence.createEntityManagerFactory("Projeto4");
        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
    }
}
