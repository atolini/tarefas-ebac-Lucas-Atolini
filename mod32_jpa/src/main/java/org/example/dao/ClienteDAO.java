package org.example.dao;

import jakarta.persistence.*;
import org.example.domain.Cliente;

public final class ClienteDAO implements IClienteDAO {
    @Override
    public Cliente register(Cliente c) {
        Cliente dataSearch = findByCPF(c.getCpf());
        if (dataSearch == null) {
            EntityManagerFactory factory = Persistence.createEntityManagerFactory("ExemploJPA");
            EntityManager entityManager = factory.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(c);
            entityManager.getTransaction().commit();
            entityManager.close();
            factory.close();
            return c;
        } else {
            return dataSearch;
        }
    }

    @Override
    public Cliente update(Cliente c) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("ExemploJPA");
        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(c);
        entityManager.getTransaction().commit();
        entityManager.close();
        factory.close();
        return c;
    }

    @Override
    public Cliente findById(Long id) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("ExemploJPA");
        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();
        Cliente c = entityManager.find(Cliente.class, id);
        entityManager.getTransaction().commit();
        entityManager.close();
        factory.close();
        return c;
    }

    @Override
    public Cliente findByCPF(Long cpf) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("ExemploJPA");
        EntityManager entityManager = factory.createEntityManager();
        String query = "select c from Cliente c where c.cpf = :cpf";
        TypedQuery<Cliente> typedQuery = entityManager.createQuery(query, Cliente.class);
        typedQuery.setParameter("cpf", cpf);
        Cliente c;
        try {
            c = typedQuery.getSingleResult();
        } catch (Exception e) {
            c = null;
        }
        entityManager.close();
        factory.close();
        return c;
    }

    @Override
    public Boolean remove(Cliente c) {
        Cliente cRecupered = findByCPF(c.getCpf());
        if (cRecupered != null) {
            EntityManagerFactory factory = Persistence.createEntityManagerFactory("ExemploJPA");
            EntityManager entityManager = factory.createEntityManager();
            entityManager.getTransaction().begin();
            cRecupered = entityManager.merge(cRecupered);
            entityManager.remove(cRecupered);
            entityManager.getTransaction().commit();
            entityManager.close();
            factory.close();
            return true;
        }
        return false;
    }

    @Override
    public Boolean removeByCpf(Long cpf) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("ExemploJPA");
        EntityManager entityManager = factory.createEntityManager();
        Query query = entityManager.createQuery("delete from Cliente c where c.cpf = :cpf");
        query.setParameter("cpf", cpf);

        entityManager.getTransaction().begin();
        int countDel = query.executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();
        factory.close();

        if (countDel > 0) {
            return true;
        } else {
            return false;
        }
    }
}
