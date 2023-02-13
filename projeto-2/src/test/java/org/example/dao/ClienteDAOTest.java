package org.example.dao;

import org.example.domain.Cliente;
import org.example.domain.dao.ClienteDAOMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class ClienteDAOTest {
    private IClienteDAO clienteDAO;
    private Cliente cliente;

    public ClienteDAOTest() {
        clienteDAO = new ClienteDAOMock();
    }

    @BeforeEach
    public void init() {
        cliente = new Cliente();
        cliente.setCpf(435_025_528_56L);
        cliente.setNome("Lucas");
        cliente.setCidade("São Paulo");
        cliente.setEnd("Rua Bronze");
        cliente.setEstado("SP");
        cliente.setNumero(10);
        cliente.setTel(11_98430_2908L);
        clienteDAO.salvar(cliente);
    }

    @Test
    public void pesquisarCliente() {
        Cliente clienteBanco = clienteDAO.buscarPorCpf(this.cliente.getCpf());
        assertNotNull(clienteBanco);
    }
}