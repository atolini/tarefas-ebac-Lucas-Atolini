package org.example.dao.generic;

import org.example.domain.Venda;

public interface IVendaDAO extends IGenericDAO<Venda, Long> {
    public void finalizarVenda(Venda venda);
    public void cancelarVenda(Venda venda);
    public Venda consultarComCollection(Long id);
}
