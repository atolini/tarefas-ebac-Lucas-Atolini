package br.com.lucasatolini.vendas.online.repository;

import br.com.lucasatolini.vendas.online.domain.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.lucasatolini.vendas.online.domain.Produto.Status;

import java.util.Optional;

public interface IProdutoRepository extends MongoRepository<Produto, String> {
    Optional<Produto> findByCodigo(String codigo);
    Page<Produto> findAllByStatus(Pageable pageable, Status status);
}
