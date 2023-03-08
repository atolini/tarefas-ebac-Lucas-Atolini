package br.com.lucasatolini.vendas.online.repository;

import br.com.lucasatolini.vendas.online.domain.Venda;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IVendaRepository extends MongoRepository<Venda, String> {
    Optional<Venda> findByCodigo(String codigo);
}
