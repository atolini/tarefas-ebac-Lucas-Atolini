package org.example.jdbc.DAO;

import org.example.domain.Produto;
import org.example.jdbc.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO implements IProdutoDAO {
    @Override
    public Integer cadastrar(Produto p) throws Exception {
        // Armazena a conexão;
        Connection connection = null;

        // Armazena a query;
        PreparedStatement stm = null;

        try {
            connection = ConnectionFactory.getConnection();
            String sql = "INSERT INTO tb_produto (id, codigo, nome, fabricante) " +
                    "VALUES (nextval('sq_produto'), ?, ?, ?)";
            stm = connection.prepareStatement(sql);
            stm.setString(1, p.getCodigo());
            stm.setString(2, p.getNome());
            stm.setString(3, p.getFabricante());
            return stm.executeUpdate();
        } finally {
            closeConnection(connection, stm, null);
        }
    }

    @Override
    public Integer atualizar(Produto p) throws Exception {
        Connection connection = null;
        PreparedStatement stm = null;

        try {
            connection = ConnectionFactory.getConnection();
            String sql = "UPDATE tb_produto " +
                            "SET NOME = ?, CODIGO = ?, FABRICANTE = ? " +
                            "WHERE ID = ?";
            stm = connection.prepareStatement(sql);
            stm.setString(1, p.getNome());
            stm.setString(2, p.getCodigo());
            stm.setString(3, p.getFabricante());
            stm.setLong(4, p.getId());
            return stm.executeUpdate();
        } finally {
            closeConnection(connection, stm, null);
        }
    }

    @Override
    public Produto buscar(String c) throws Exception {
        Connection connection = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        Produto p = null;
        try {
            connection = ConnectionFactory.getConnection();
            String sql = "SELECT * FROM tb_produto WHERE codigo = ?";
            stm = connection.prepareStatement(sql);
            stm.setString(1, c);
            rs = stm.executeQuery();

            if (rs.next()) {
                p = new Produto();
                Long id = rs.getLong("id");
                String nome = rs.getString("nome");
                String cd = rs.getString("codigo");
                String fabricante = rs.getString("fabricante");
                p.setId(id);
                p.setNome(nome);
                p.setCodigo(cd);
                p.setFabricante(fabricante);
            }
        } finally {
            closeConnection(connection, stm, rs);
        }
        return p;
    }

    @Override
    public List<Produto> buscarTodos() throws Exception {
        Connection connection = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<Produto> lista = new ArrayList<>();
        Produto p = null;
        try {
            connection = ConnectionFactory.getConnection();
            String sql = "SELECT * FROM tb_produto";
            stm = connection.prepareStatement(sql);
            rs = stm.executeQuery();

            while (rs.next()) {
                p = new Produto();
                Long id = rs.getLong("id");
                String nome = rs.getString("nome");
                String fabricante = rs.getString("fabricante");
                String cd = rs.getString("codigo");
                p.setId(id);
                p.setNome(nome);
                p.setFabricante(fabricante);
                p.setCodigo(cd);
                lista.add(p);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            closeConnection(connection, stm, rs);
        }

        return lista;
    }

    @Override
    public Integer excluir(Produto p) throws Exception {
        Connection connection = null;
        PreparedStatement stm = null;
        try {
            connection = ConnectionFactory.getConnection();
            String sql = "DELETE FROM tb_produto WHERE codigo = ?";
            stm = connection.prepareStatement(sql);
            stm.setString(1, p.getCodigo());
            return stm.executeUpdate();
        } finally {
            closeConnection(connection, stm, null);
        }
    }

    /*
     * Metodos complementares;
     */
    private void closeConnection(Connection connection, PreparedStatement stm, ResultSet rs) {
        try {
            if (rs != null && !rs.isClosed()) {
                rs.close();
            }
            if (stm != null && !stm.isClosed()) {
                stm.close();
            }
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
