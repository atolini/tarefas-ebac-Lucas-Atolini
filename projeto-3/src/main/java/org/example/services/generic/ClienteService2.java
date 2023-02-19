package org.example.services.generic;

import org.example.dao.generic.IGenericDAO;
import org.example.domain.Cliente;

public final class ClienteService2 extends GenericService<Cliente, Long> {
    public ClienteService2(IGenericDAO<Cliente, Long> genericDAO) {
        super(genericDAO);
    }
}
