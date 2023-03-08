package org.example.dao;

import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.example.dao.generic.GenericDAO;
import org.example.dao.generic.IVendaDAO;
import org.example.domain.Cliente;
import org.example.domain.Produto;
import org.example.domain.Venda;

public class VendaDAO extends GenericDAO<Venda, Long> implements IVendaDAO {
    public VendaDAO() {
        super(Venda.class);
    }

    @Override
    public Venda cadastrar(Venda entity) {
        try {
            super.openConnection();
            entity.getProdutos().forEach(p -> {
                Produto produto = super.entityManager.merge(p.getProduto());
                p.setProduto(produto);
            });
            Cliente cliente = super.entityManager.merge(entity.getCliente());
            entity.setCliente(cliente);
            super.entityManager.persist(entity);
            super.entityManager.getTransaction().commit();
            closeConnection();
            return entity;
        } catch (Exception e) {
            throw new RuntimeException("Erro na persistência da venda!");
        }
    }

    // Implantações da Interface IVendaDAO;
    @Override
    public void finalizarVenda(Venda venda) {
        super.alterar(venda);
    }

    @Override
    public void cancelarVenda(Venda venda) {
        super.alterar(venda);
    }

    @Override
    public Venda consultarComCollection(Long id) {
        super.openConnection();
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Venda> query = builder.createQuery(Venda.class);
        Root<Venda> root = query.from(Venda.class);
        root.fetch("cliente");
        root.fetch("produtos");
        query.select(root).where(builder.equal(root.get("id"), id));
        TypedQuery<Venda> typedQuery = entityManager.createQuery(query);
        Venda v = typedQuery.getSingleResult();
        super.closeConnection();
        return v;
    }
}
