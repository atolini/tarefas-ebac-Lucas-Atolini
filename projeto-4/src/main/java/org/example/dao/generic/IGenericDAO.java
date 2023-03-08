package org.example.dao.generic;

import java.io.Serializable;
import java.util.Collection;

public interface IGenericDAO <T extends Persistence, E extends Serializable> {
    public T cadastrar(T entity);
    public void excluir(T entity);
    public T alterar(T entity);
    public T buscar(E value);
    public Collection<T> buscarTodos();
}
