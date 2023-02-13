package org.example.dao;

import org.example.domain.Cliente;

public interface IClienteDAO {
    Boolean salvar(Cliente c);
    Cliente buscarPorCpf(Long cpf);
}
