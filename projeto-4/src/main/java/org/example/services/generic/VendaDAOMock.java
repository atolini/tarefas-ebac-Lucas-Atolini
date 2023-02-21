package org.example.services.generic;

import org.example.dao.VendaDAO;
import org.example.domain.Venda;

public final class VendaDAOMock extends VendaDAO {
    @Override
    public void excluir(Venda entity) {
        super.excluir(entity);
    }
}
