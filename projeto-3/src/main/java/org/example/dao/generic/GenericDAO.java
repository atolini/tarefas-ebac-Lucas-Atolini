package org.example.dao.generic;

import org.example.annotations.ColunaTabela;
import org.example.annotations.Tabela;
import org.example.annotations.TipoChave;
import org.example.sql.ConnectionFactory;

import java.io.Serializable;
import java.lang.annotation.IncompleteAnnotationException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class GenericDAO<T extends Persistence, E extends Serializable> implements IGenericDAO<T, E> {

    /* Metodos abstratos */
    protected abstract String getQueryInsercao();
    protected abstract void setParametrosQueryInsercao(PreparedStatement stm, T entity) throws SQLException;

    @Override
    public Boolean cadastrar(T entity) {
        Connection connection = null;
        PreparedStatement stm = null;
        try {
            connection = getConnection();
            stm = connection.prepareStatement(getQueryInsercao(), Statement.RETURN_GENERATED_KEYS);
            setParametrosQueryInsercao(stm, entity);
            int rowsAffected = stm.executeUpdate();

            if(rowsAffected > 0) {
                try (ResultSet rs = stm.getGeneratedKeys()){
                    if (rs.next()) {
                        ((Persistence) entity).setId(rs.getLong(1));
                    }
                }
                return true;
            }

        } catch (SQLException e) {
            throw new RuntimeException("ERRO CADASTRANDO OBJETO ", e);
        } finally {
            closeConnection(connection, stm, null);
        }
        return false;
    }

    @Override
    public T buscar(E value) {
        return null;
    }

    @Override
    public Boolean excluir(E value) {
        return true;
    }

    @Override
    public Boolean alterar(T entity) {
        return true;
    }

    @Override
    public Collection<T> buscarTodos() {
        return null;
    }

    /* Metodos de Apoio */
    protected Connection getConnection() throws SQLException {
        return ConnectionFactory.getConnection();
    }

    protected void closeConnection(Connection connection, PreparedStatement stm, ResultSet rs) {
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
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    /* Lê a annotation @Table e retorna o valor */
    protected String getTableName(T entity) {
        Tabela t = entity.getClass().getAnnotation(Tabela.class);
        if (t != null) {
            return t.value();
        } else {
            throw new IncompleteAnnotationException(Tabela.class, "Entidade " + entity.getClass() + "não mapeada com @Table");
        }
    }

    protected String getFieldsNames(T entity) {
        Field[] fields = entity.getClass().getDeclaredFields();
        List<String> list = new ArrayList<>(fields.length);

        for (Field field : fields) {
            if (field.isAnnotationPresent(ColunaTabela.class)) {
                ColunaTabela c = field.getAnnotation(ColunaTabela.class);
                list.add(c.dbName());
            }
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
            int temp = list.size();
            temp--;
            if (i < temp) {
                sb.append(", ");
            }
        }

        return sb.toString();
    }

    protected String getFieldsValues(T entity) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Field[] fields = entity.getClass().getDeclaredFields();
        List<String> listGetNames = new ArrayList<>(fields.length);

        for (Field field : fields) {
            if (field.isAnnotationPresent(ColunaTabela.class)) {
                ColunaTabela c = field.getAnnotation(ColunaTabela.class);
                listGetNames.add(c.getJavaName());
            }
        }

        List<String> listResultMethods = new ArrayList<>(fields.length);

        for (String s : listGetNames) {
            Method m = entity.getClass().getMethod(s);
            Object o = m.invoke(entity);

            // Caso o retorno seja long;
            if (o instanceof Long) {
                listResultMethods.add(String.valueOf(o));
            }

            // Caso o retorno seja uma String;
            if (o instanceof String) {
                listResultMethods.add((String) o);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < listResultMethods.size(); i++) {
            sb.append(listResultMethods.get(i));
            int temp = listResultMethods.size();
            temp--;
            if (i < temp) {
                sb.append(", ");
            }
        }

        return sb.toString();
    }

    public <E> E getChave(T entity) {
        Field[] fields = entity.getClass().getDeclaredFields();
        E returnValue = null;
        for (Field field : fields) {
            if (field.isAnnotationPresent(TipoChave.class)) {
                TipoChave tipoChave = field.getAnnotation(TipoChave.class);
                String nomeMetodo = tipoChave.value();
                try {
                    Method method = entity.getClass().getMethod(nomeMetodo);
                    returnValue = (E) method.invoke(entity);
                    return returnValue;
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                    throw new RuntimeException();
                }
            }
        }
        throw new RuntimeException();
    }
}
