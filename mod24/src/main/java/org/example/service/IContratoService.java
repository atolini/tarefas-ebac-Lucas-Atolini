package org.example.service;

import org.example.Contrato;

public interface IContratoService {
    String salvar();
    Contrato buscar();
    Boolean excluir(Contrato c);
    Boolean atualizar(Contrato c);
}
