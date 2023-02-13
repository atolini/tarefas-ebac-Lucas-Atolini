package org.example.services;

import org.example.dao.IClienteDAO;
import org.example.domain.Cliente;

public class ClienteService implements IClienteService {
    private IClienteDAO clienteDAO;

    public ClienteService(IClienteDAO clienteDAO) {
        this.clienteDAO = clienteDAO;
    }

    @Override
    public Boolean salvar(Cliente c) {
        return clienteDAO.salvar(c);
    }

    @Override
    public Cliente buscarPorCpf(Long cpf) {
        return clienteDAO.buscarPorCpf(cpf);
    }
}
