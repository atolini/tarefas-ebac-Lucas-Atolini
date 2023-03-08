package org.example.dao;

import org.example.dao.generic.GenericDAO;
import org.example.domain.Produto;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public final class ProdutoDAO extends GenericDAO<Produto, String> {
    public ProdutoDAO() {
        super();
    }

    @Override
    protected String getQueryInsercao() {
        return "INSERT INTO tb_produto (ID, CODIGO, NOME, DESCRICAO, VALOR) VALUES (nextval('sq_produto'),?,?,?,?";
    }

    @Override
    protected void setParametrosQueryInsercao(PreparedStatement stm, Produto entity) throws SQLException {
        stm.setString(1, entity.getCodigo());
        stm.setString(2, entity.getNome());
        stm.setString(3, entity.getDescricao());
        stm.setBigDecimal(4, entity.getValor());
    }
}
