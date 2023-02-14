package org.example.services.generic;

import org.example.dao.generic.IGenericDAO;
import org.example.dao.generic.Persistence;

import java.io.Serializable;
import java.util.Collection;

public abstract class GenericService<T extends Persistence, E extends Serializable> implements IGenericService<T, E> {
    private final IGenericDAO<T, E> genericDAO;

    public GenericService(IGenericDAO<T, E> genericDAO) {
        this.genericDAO = genericDAO;
    }

    @Override
    public Boolean cadastrar(T entity) {
        return genericDAO.cadastrar(entity);
    }

    @Override
    public Boolean excluir(E value) {
        return genericDAO.excluir(value);
    }

    @Override
    public Boolean alterar(T entity) {
        return genericDAO.alterar(entity);
    }

    @Override
    public T buscar(E value) {
        return genericDAO.buscar(value);
    }

    @Override
    public Collection<T> buscarTodos() {
        return genericDAO.buscarTodos();
    }
}
