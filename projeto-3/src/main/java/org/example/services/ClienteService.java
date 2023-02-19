package org.example.services;

import org.example.dao.generic.IGenericDAO;
import org.example.domain.Cliente;

public class ClienteService implements IClienteService {
    private final IGenericDAO<Cliente, Long> clienteDAO;

    public ClienteService(IGenericDAO<Cliente, Long> clienteDAO) {
        this.clienteDAO = clienteDAO;
    }

    @Override
    public Boolean salvar(Cliente c) {
        return clienteDAO.cadastrar(c);
    }

    @Override
    public Cliente buscarPorCpf(Long cpf) {
        return clienteDAO.buscar(cpf);
    }

    @Override
    public Boolean alterar(Cliente c) {
        return true;
    }
}
