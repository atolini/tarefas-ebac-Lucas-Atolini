package org.example.jdbc.DAO;

import org.example.domain.Produto;

import java.util.List;

public interface IProdutoDAO {
    public Integer cadastrar(Produto p) throws Exception;
    public Integer atualizar(Produto p) throws Exception;
    public Produto buscar(String c) throws Exception;
    public List<Produto> buscarTodos() throws Exception;
    public Integer excluir(Produto p) throws Exception;
}
