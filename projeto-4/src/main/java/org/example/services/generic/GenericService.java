package org.example.services.generic;

import org.example.dao.generic.IGenericDAO;
import org.example.dao.generic.Persistence;

import java.io.Serializable;
import java.util.Collection;

public abstract class GenericService<T extends Persistence, E extends Serializable> implements IGenericService<T, E> {
    protected final IGenericDAO<T, E> genericDAO;

    public GenericService(IGenericDAO<T, E> genericDAO) {
        this.genericDAO = genericDAO;
    }

    @Override
    public T cadastrar(T entity) {
        return this.genericDAO.cadastrar(entity);
    }

    @Override
    public void excluir(T entity) {
        this.genericDAO.excluir(entity);
    }

    @Override
    public T alterar(T entity) {
        return this.genericDAO.alterar(entity);
    }

    @Override
    public T buscar(E value) {
        return this.genericDAO.buscar(value);
    }

    @Override
    public Collection<T> buscarTodos() {
        return this.genericDAO.buscarTodos();
    }
}
