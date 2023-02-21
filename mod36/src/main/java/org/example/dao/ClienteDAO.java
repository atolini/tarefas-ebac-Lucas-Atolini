package org.example.dao;

import org.example.dao.generic.GenericDAO;
import org.example.domain.Cliente;

public final class ClienteDAO extends GenericDAO<Cliente, Long> {
    public ClienteDAO() {
        super(Cliente.class);
    }
}
