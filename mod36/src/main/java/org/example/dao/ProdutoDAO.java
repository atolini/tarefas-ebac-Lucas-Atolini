package org.example.dao;

import org.example.dao.generic.GenericDAO;
import org.example.domain.Produto;

public final class ProdutoDAO extends GenericDAO<Produto, Long> {
    public ProdutoDAO() {
        super(Produto.class);
    }
}
