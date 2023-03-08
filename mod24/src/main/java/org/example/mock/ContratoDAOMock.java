package org.example.mock;

import org.example.Contrato;
import org.example.dao.IContratoDAO;

public class ContratoDAOMock implements IContratoDAO {
    @Override
    public String salvar() {
        return "Sucesso";
    }

    @Override
    public Contrato buscar() {
        return new Contrato();
    }

    @Override
    public Boolean excluir(Contrato c) {
        return true;
    }

    @Override
    public Boolean atualizar(Contrato c) {
        return true;
    }
}
