package org.example.dao;

import org.example.domain.Cliente;

public interface IClienteDAO {
    Cliente register(Cliente c);
    Cliente update(Cliente c);
    Cliente findById(Long id);
    Cliente findByCPF(Long cpf);
    Boolean remove(Cliente c);
    Boolean removeByCpf(Long cpf);
}
