package org.example.dao;

import org.example.domain.Cliente;

public class ClienteDAO implements IClienteDAO {
    @Override
    public Boolean salvar(Cliente c) {
        return true;
    }

    @Override
    public Cliente buscarPorCpf(Long cpf) {
        return null;
    }
}
