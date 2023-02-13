package org.example.domain.dao;

import org.example.dao.IClienteDAO;
import org.example.domain.Cliente;

public class ClienteDAOMock implements IClienteDAO {
    @Override
    public Boolean salvar(Cliente c) {
        return true;
    }

    @Override
    public Cliente buscarPorCpf(Long cpf) {
        Cliente c = new Cliente();
        c.setCpf(cpf);
        return c;
    }
}
