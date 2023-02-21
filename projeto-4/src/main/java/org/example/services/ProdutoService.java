package org.example.services;

import org.example.dao.generic.IGenericDAO;
import org.example.domain.Produto;
import org.example.services.generic.GenericService;

public final class ProdutoService extends GenericService<Produto, String> {
    public ProdutoService(IGenericDAO<Produto, String> genericDAO) {
        super(genericDAO);
    }
}
