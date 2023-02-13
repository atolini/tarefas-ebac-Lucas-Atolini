package org.example.domain;

import org.example.dao.IClienteDAO;
import org.example.domain.dao.ClienteDAOMock;
import org.example.services.ClienteService;
import org.example.services.IClienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class ClienteTest {
    private IClienteService clienteService;
    private Cliente cliente;

    public ClienteTest() {
        IClienteDAO dao = new ClienteDAOMock();
        clienteService = new ClienteService(dao);
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
        clienteService.salvar(cliente);
    }

    @Test
    public void buscarCliente() {
        Cliente clienteBanco = clienteService.buscarPorCpf(this.cliente.getCpf());
        assertNotNull(clienteBanco);
    }

    @Test
    public void salvarCliente() {
        Boolean retorno = clienteService.salvar(cliente);
    }
}