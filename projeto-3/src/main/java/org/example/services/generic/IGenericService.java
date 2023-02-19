package org.example.services.generic;

import org.example.dao.generic.Persistence;

import java.io.Serializable;
import java.util.Collection;

public interface IGenericService<T extends Persistence, E extends Serializable> {
    public Boolean cadastrar(T entity);
    public Boolean excluir(E value);
    public Boolean alterar(T entity);
    public T buscar(E value);
    public Collection<T> buscarTodos();
}
