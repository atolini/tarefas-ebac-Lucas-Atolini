package br.com.lucasatolini.SpringBootPrimeiroExemplo.repository;

import br.com.lucasatolini.SpringBootPrimeiroExemplo.domain.Cliente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Long> {
}
