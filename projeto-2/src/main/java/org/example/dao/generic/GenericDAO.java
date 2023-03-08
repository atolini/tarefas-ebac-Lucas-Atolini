package org.example.dao.generic;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public abstract class GenericDAO<T extends Persistence, E extends Serializable> implements IGenericDAO<T, E> {
    private SingletonMap singletonMap;

    protected GenericDAO() {
        this.singletonMap = SingletonMap.getInstance();
    }

    @Override
    public Boolean cadastrar(T entity) {
        Map<E, T> internMap = (Map<E, T>) getMap();
        E chave = getChave(entity);
        if (internMap.containsKey(chave)) {
            return false;
        }
        internMap.put(chave, entity);
        return true;
    }

    @Override
    public Boolean excluir(E value) {
        Map<E, T> internMap = (Map<E, T>) getMap();
        T object = internMap.get(value);
        if (object != null) {
            internMap.remove(value);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Boolean alterar(T entity) {
        Map<E, T> internMap = (Map<E, T>) getMap();
        E chave = getChave(entity);
        T object = internMap.get(chave);
        if (object != null) {
            internMap.replace(chave, entity);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public T buscar(E value) {
        Map<E, T> internMap = (Map<E, T>) getMap();
        return internMap.get(value);
    }

    @Override
    public Collection<T> buscarTodos() {
        Map<E, T> internMap = (Map<E, T>) getMap();
        return internMap.values();
    }

    /* Metodos de apoio */
    private Map<?, ?>  getMap() {
        return this.singletonMap.getMap().computeIfAbsent(this.getClass(), k -> new HashMap<>());
    }

    public <E> E getChave(T entity) {
        Field[] fields = entity.getClass().getDeclaredFields();
        E returnValue = null;
        for (Field field : fields) {
            if (field.isAnnotationPresent(TipoChave.class)) {
                TipoChave tipoChave = field.getAnnotation(TipoChave.class);
                String nomeMetodo = tipoChave.value();
                try {
                    Method method = entity.getClass().getMethod(nomeMetodo);
                    returnValue = (E) method.invoke(entity);
                    return returnValue;
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                    throw new RuntimeException();
                }
            }
        }
        throw new RuntimeException();
    }
}
