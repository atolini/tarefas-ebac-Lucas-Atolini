package br.com.lucasatolini.vendas.online.usecase;

import br.com.lucasatolini.vendas.online.domain.Cliente;
import br.com.lucasatolini.vendas.online.errorhandler.EntityNotFoundException;
import br.com.lucasatolini.vendas.online.repository.IClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BuscarCliente {
    private IClienteRepository clienteRepository;

    @Autowired
    public BuscarCliente(IClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Page<Cliente> buscar(Pageable pageable) {
        return clienteRepository.findAll(pageable);
    }

    public Cliente buscarPorId(String id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("id", id));
    }

    public Boolean isCadastrado(String id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.isPresent() ? true : false;
    }

    public Cliente buscarPorCpf(Long cpf) {
        return clienteRepository.findByCpf(cpf)
                .orElseThrow(() -> new EntityNotFoundException("cpf", String.valueOf(cpf)));
    }
}
