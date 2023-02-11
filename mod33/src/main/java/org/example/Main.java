package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import org.example.dao.GenericDAO;
import org.example.dao.IGenericDAO;
import org.example.domain.Acessorio;
import org.example.domain.Carro;
import org.example.domain.Marca;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("ExemploJPA");
        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();

        // Criando o DAO
        IGenericDAO dao = new GenericDAO();

        // Criando as entidades
        Acessorio portasEletricas = new Acessorio("Portas eletricas", "Desc do acessorio");
        Acessorio alarmeModerno = new Acessorio("Alarme moderno", "Desc do acessorio");
        Marca honda = new Marca();
        Carro civic = new Carro("Civic", honda, 35_000D,
                List.of(new String[]{"Branco", "Azul"}),
                List.of(new Acessorio[]{portasEletricas, alarmeModerno}));
        honda.setCarros(List.of(civic));
        honda.setNome("Honda");

        // Registrando no banco (cascata)
        dao.register(civic);

        // Trazendo do bando para impressões
        String jpql = "select c from carro c join fetch c.marca join fetch c.acessorios where c.nome = :nome";
        TypedQuery<Carro> query = entityManager.createQuery(jpql, Carro.class);
        query.setParameter("nome", "Civic");
        Carro civicDoBanco = query.getSingleResult();

        // Imprimindo enquanto sessão está aberta
        civicDoBanco.getAcessorios().forEach(System.out::println);
        System.out.println(civicDoBanco.getMarca().toString());

        // Finalizando sessão
        entityManager.getTransaction().commit();
        entityManager.close();
        factory.close();
    }
}