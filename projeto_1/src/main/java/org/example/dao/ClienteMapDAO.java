package org.example.dao;

import org.example.domain.Cliente;

import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

public class ClienteMapDAO implements IClienteDAO {
    private Map<Long, Cliente> map;

    public ClienteMapDAO() {
        map = new TreeMap<>();
    }

    @Override
    public Boolean cadastrar(Cliente cliente) {
        if (map.containsKey(cliente.getCpf())) {
            return false;
        }
        map.put(cliente.getCpf(), cliente);
        return true;
    }

    @Override
    public void excluir(Long cpf) {
        Cliente cliente = map.get(cpf);
        map.remove(cliente.getCpf(), cliente);
    }

    @Override
    public void alterar(Cliente cliente) {
        Cliente clienteCad = map.get(cliente.getCpf());
        clienteCad.setNome(cliente.getNome());
        clienteCad.setTel(cliente.getTel());
        clienteCad.setNumero(cliente.getNumero());
        clienteCad.setEnd(cliente.getEnd());
        clienteCad.setCidade(cliente.getCidade());
        clienteCad.setEstado(cliente.getEstado());
    }

    @Override
    public Cliente consultar(Long cpf) {
        return this.map.get(cpf);
    }

    @Override
    public Collection<Cliente> buscarTodos() {
        return this.map.values();
    }
}
