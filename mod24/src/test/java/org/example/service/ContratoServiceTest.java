package org.example.service;

import org.example.Contrato;
import org.example.dao.ContratoDAO;
import org.example.dao.IContratoDAO;
import org.example.mock.ContratoDAOMock;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ContratoServiceTest {
    @Test
    public void salvar() {
        IContratoDAO contratoDAO = new ContratoDAOMock();
        IContratoService service = new ContratoService(contratoDAO);
        String retorno = service.salvar();
        assertEquals(retorno, "Sucesso");
    }

    @Test
    public void esperadoErroAoSalvarComBanco() {
        IContratoDAO contratoDAO = new ContratoDAO();
        IContratoService service = new ContratoService(contratoDAO);
        assertThrows(UnsupportedOperationException.class, service::salvar);
    }

    @Test
    public void atualizar() {
        IContratoDAO contratoDAO = new ContratoDAOMock();
        IContratoService service = new ContratoService(contratoDAO);
        Contrato c = new Contrato();
        Boolean retorno = service.atualizar(c);
        assertTrue(retorno);
    }

    @Test
    public void esperadoErroAoAtualizarComBanco() {
        IContratoDAO contratoDAO = new ContratoDAO();
        IContratoService service = new ContratoService(contratoDAO);
        Contrato c = new Contrato();
        assertThrows(UnsupportedOperationException.class, () -> service.atualizar(c));
    }

    @Test
    public void excluir() {
        IContratoDAO contratoDAO = new ContratoDAOMock();
        IContratoService service = new ContratoService(contratoDAO);
        Contrato c = new Contrato();
        Boolean retorno = service.excluir(c);
        assertTrue(retorno);
    }

    @Test
    public void esperadoErroAoExcluirComBanco() {
        IContratoDAO contratoDAO = new ContratoDAO();
        IContratoService service = new ContratoService(contratoDAO);
        Contrato c = new Contrato();
        assertThrows(UnsupportedOperationException.class, () -> service.excluir(c));
    }

    @Test
    public void buscar() {
        IContratoDAO contratoDAO = new ContratoDAOMock();
        IContratoService service = new ContratoService(contratoDAO);
        Contrato c = service.buscar();
        assertTrue(c instanceof Contrato);
    }

    @Test
    public void esperadoErroAoBuscarComBanco() {
        IContratoDAO contratoDAO = new ContratoDAO();
        IContratoService service = new ContratoService(contratoDAO);
        assertThrows(UnsupportedOperationException.class, () -> service.buscar());
    }
}
