package org.example.dao;

import org.example.Contrato;

public class ContratoDAO implements IContratoDAO{
    @Override
    public String salvar() {
        throw new UnsupportedOperationException("Banco não conectado.");
    }

    @Override
    public Contrato buscar() {
        throw new UnsupportedOperationException("Banco não conectado.");
    }

    @Override
    public Boolean excluir(Contrato c) {
        throw new UnsupportedOperationException("Banco não conectado.");
    }

    @Override
    public Boolean atualizar(Contrato c) {
        throw new UnsupportedOperationException("Banco não conectado.");
    }
}
