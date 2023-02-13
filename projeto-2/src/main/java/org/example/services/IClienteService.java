package org.example.services;

import org.example.domain.Cliente;

public interface IClienteService {
    public Boolean salvar(Cliente c);
    public Cliente buscarPorCpf(Long cpf);
}
