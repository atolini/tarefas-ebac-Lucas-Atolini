package org.example.service;

import org.example.Contrato;
import org.example.dao.IContratoDAO;

public class ContratoService implements IContratoService {
    IContratoDAO contratoDAO;

    public ContratoService(IContratoDAO contratoDAO) {
        this.contratoDAO = contratoDAO;
    }

    @Override
    public String salvar() {
        return contratoDAO.salvar();
    }

    @Override
    public Contrato buscar() {
        return contratoDAO.buscar();
    }

    @Override
    public Boolean excluir(Contrato c) {
        return contratoDAO.excluir(c);
    }

    @Override
    public Boolean atualizar(Contrato c) {
        return contratoDAO.atualizar(c);
    }
}
