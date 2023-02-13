package org.example.dao;

import org.example.Contrato;

public interface IContratoDAO {
    String salvar();
    Contrato buscar();
    Boolean excluir(Contrato c);
    Boolean atualizar(Contrato c);
}
