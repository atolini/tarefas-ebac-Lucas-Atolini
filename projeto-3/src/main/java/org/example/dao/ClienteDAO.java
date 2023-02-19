package org.example.dao;

import org.example.dao.generic.GenericDAO;
import org.example.domain.Cliente;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ClienteDAO extends GenericDAO<Cliente, Long> {
    public ClienteDAO() {
        super();
    }

    @Override
    protected String getQueryInsercao() {
        return "INSERT INTO tb_cliente (ID, NOME, CPF, TEL, ENDERECO, NUMERO, CIDADE, ESTADO) VALUES (nextval" +
                "('sq_cliente'),?,?,?,?,?,?,?)";
    }

    @Override
    protected void setParametrosQueryInsercao(PreparedStatement stm, Cliente entity) throws SQLException {
        stm.setString(1, entity.getNome());
        stm.setLong(2, entity.getCpf());
        stm.setLong(3, entity.getTel());
        stm.setString(4, entity.getEnd());
        stm.setLong(5, entity.getNumero());
        stm.setString(6, entity.getCidade());
        stm.setString(7, entity.getEstado());
    }
}
