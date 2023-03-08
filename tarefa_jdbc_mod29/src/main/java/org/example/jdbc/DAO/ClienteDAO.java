package org.example.jdbc.DAO;

import org.example.domain.Cliente;
import org.example.jdbc.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO implements IClienteDAO {
    @Override
    public Integer cadastrar(Cliente cliente) throws Exception {
        // Armazena a conexão;
        Connection connection = null;

        // Armazena a query;
        PreparedStatement stm = null;

        try {
            connection = ConnectionFactory.getConnection();
            String sql = getSqlInsert();
            stm = connection.prepareStatement(sql);
            adicionarParametrosInsert(stm, cliente);
            return stm.executeUpdate();
        } finally {
            closeConnection(connection, stm, null);
        }
    }

    @Override
    public Integer atualizar(Cliente cliente) throws Exception {
        Connection connection = null;
        PreparedStatement stm = null;
        try {
            connection = ConnectionFactory.getConnection();
            String sql = getSqlUpdate();
            stm = connection.prepareStatement(sql);
            adicionarParametrosUpdate(stm, cliente);
            return stm.executeUpdate();
        } finally {
            closeConnection(connection, stm, null);
        }
    }

    @Override
    public Cliente buscar(String codigo) throws Exception {
        Connection connection = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        Cliente cliente = null;
        try {
            connection = ConnectionFactory.getConnection();
            String sql = getSqlSelect();
            stm = connection.prepareStatement(sql);
            adicionarParametrosSelect(stm, codigo);
            rs = stm.executeQuery();

            if (rs.next()) {
                cliente = new Cliente();
                Long id = rs.getLong("id");
                String nome = rs.getString("nome");
                String cd = rs.getString("codigo");
                cliente.setId(id);
                cliente.setNome(nome);
                cliente.setCodigo(cd);
            }
        } finally {
            closeConnection(connection, stm, rs);
        }

        return cliente;
    }

    @Override
    public Integer excluir(Cliente cliente) throws Exception {
        Connection connection = null;
        PreparedStatement stm = null;
        try {
            connection = ConnectionFactory.getConnection();
            String sql = getSqlDelete();
            stm = connection.prepareStatement(sql);
            adicionarParametrosDelete(stm, cliente);
            return stm.executeUpdate();
        } finally {
            closeConnection(connection, stm, null);
        }
    }

    @Override
    public List<Cliente> buscarTodos() throws Exception {
        Connection connection = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<Cliente> lista = new ArrayList<>();
        Cliente cliente = null;
        try {
            connection = ConnectionFactory.getConnection();
            String sql = getSqlSelectAll();
            stm = connection.prepareStatement(sql);
            rs = stm.executeQuery();

            while (rs.next()) {
                cliente = new Cliente();
                Long id = rs.getLong("id");
                String nome = rs.getString("nome");
                String cd = rs.getString("codigo");
                cliente.setId(id);
                cliente.setNome(nome);
                cliente.setCodigo(cd);
                lista.add(cliente);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            closeConnection(connection, stm, rs);
        }
        return lista;
    }

    /*
     * Metodos de apoio!
     * Query builder (Select All)
     */
    private String getSqlSelectAll() {
        return "SELECT * FROM tb_cliente";
    }

    /*
     * Metodos de apoio!
     * Query builder (Delete)
     */
    private void adicionarParametrosDelete(PreparedStatement stm, Cliente cliente) throws SQLException {
        stm.setString(1, cliente.getCodigo());
    }

    private String getSqlDelete() {
        return "DELETE FROM tb_cliente WHERE codigo = ?";
    }

    /*
     * Metodos de apoio!
     * Query builder (Update)
     */
    private String getSqlUpdate() {
        return "UPDATE tb_cliente " +
                "SET NOME = ?, CODIGO = ? " +
                "WHERE ID = ?";
    }

    private void adicionarParametrosUpdate(PreparedStatement stm, Cliente cliente) throws SQLException {
        stm.setString(1, cliente.getNome());
        stm.setString(2, cliente.getCodigo());
        stm.setLong(3, cliente.getId());
    }

    /*
     * Metodos de apoio!
     * Query builder (Insert)
     */
    private String getSqlInsert() {
        return "INSERT INTO TB_CLIENTE (id, codigo, nome) " +
                "VALUES (nextval('sq_cliente'), ?, ?)";
    }

    private void adicionarParametrosInsert(PreparedStatement stm, Cliente cliente) throws SQLException {
        stm.setString(1, cliente.getCodigo());
        stm.setString(2, cliente.getNome());
    }

    /*
     * Metodos de apoio!
     * Query builder (Select)
     */
    private String getSqlSelect() {
        return "SELECT * FROM tb_cliente WHERE codigo = ?";
    }

    private void adicionarParametrosSelect(PreparedStatement stm, String codigo) throws SQLException {
        stm.setString(1, codigo);
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
