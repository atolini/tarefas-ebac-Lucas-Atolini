package org.example.dao;

import org.example.dao.generic.IGenericDAO;
import org.example.domain.Cliente;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public final class ClienteDAOMock implements IGenericDAO<Cliente, Long> {
    @Override
    public Boolean cadastrar(Cliente entity) {
        return true;
    }

    @Override
    public Boolean excluir(Long value) {
        return true;
    }

    @Override
    public Boolean alterar(Cliente entity) {
        return true;
    }

    @Override
    public Cliente buscar(Long value) {
        return new Cliente();
    }

    @Override
    public Collection<Cliente> buscarTodos() {
        Cliente c = new Cliente();
        List<Cliente> list = new ArrayList<>();
        list.add(c);
        return list;
    }
}
