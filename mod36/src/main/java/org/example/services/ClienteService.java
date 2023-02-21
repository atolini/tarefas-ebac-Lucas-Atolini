package org.example.services;

import org.example.dao.generic.IGenericDAO;
import org.example.domain.Cliente;
import org.example.services.generic.GenericService;

public final class ClienteService extends GenericService<Cliente, Long> {
    public ClienteService(IGenericDAO<Cliente, Long> genericDAO) {
        super(genericDAO);
    }
}
