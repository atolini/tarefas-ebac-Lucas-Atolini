package org.example.dao;

import org.example.domain.Cliente;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClienteDAOTest {
    @Test
    public void register() {
        IClienteDAO clienteDAO = new ClienteDAO();
        Long cpf = 000_000_000_01L;

        // Cria e registra
        Cliente c = new Cliente("Lucas Atolini", cpf);
        c = clienteDAO.register(c);

        // Testa o retorno
        assertNotNull(c);
        assertEquals(cpf, c.getCpf());

        // Solicita a remoção
        assertTrue(clienteDAO.remove(c));
    }

    @Test
    public void remove() {
        IClienteDAO clienteDAO = new ClienteDAO();
        Long cpf = 345_000_123_01L;

        // Cria e registra
        Cliente c = new Cliente("Lucas Atolini", cpf);
        clienteDAO.register(c);

        // Verifica a existencia do registro
        assertNotNull(clienteDAO.findByCPF(cpf));

        // Testa a remoção
        assertTrue(clienteDAO.remove(c));
    }

    @Test
    public void removeByCpf() {
        IClienteDAO clienteDAO = new ClienteDAO();
        Long cpf = 123_123_123_22L;

        // Cria e registra
        Cliente c = new Cliente("Temp", cpf);
        clienteDAO.register(c);

        // Verifica a existencia do registro
        assertNotNull(clienteDAO.findByCPF(cpf));

        // Testa a remoção
        assertTrue(clienteDAO.removeByCpf(cpf));
    }

    @Test
    public void update() {
        IClienteDAO clienteDAO = new ClienteDAO();
        Long cpf = 123_988_982_11L;

        // Cria e registra
        Cliente c = new Cliente("Temp", cpf);
        clienteDAO.register(c);

        // Testa alteração
        c.setCpf(52L);
        clienteDAO.update(c);
        Cliente cRecupered = clienteDAO.findByCPF(c.getCpf());
        assertEquals(cRecupered.getNome(), "Temp");

        // Excluindo
        clienteDAO.remove(cRecupered);
    }
}