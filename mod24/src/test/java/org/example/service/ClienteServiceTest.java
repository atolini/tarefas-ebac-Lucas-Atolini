package org.example.service;

import org.example.dao.ClienteDAO;
import org.example.mock.ClienteDAOMock;
import org.example.dao.IClienteDAO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ClienteServiceTest {
    @Test
    public void salvar() {
        IClienteDAO clienteDAOMock = new ClienteDAOMock();
        ClienteService service = new ClienteService(clienteDAOMock);
        String retorno = service.salvar();
        assertEquals("Sucesso", retorno);
    }

    @Test
    public void erroAoSalvar() {
        IClienteDAO clienteDAO = new ClienteDAO();
        ClienteService service = new ClienteService(clienteDAO);
        assertThrows(UnsupportedOperationException.class, service::salvar);
    }
}