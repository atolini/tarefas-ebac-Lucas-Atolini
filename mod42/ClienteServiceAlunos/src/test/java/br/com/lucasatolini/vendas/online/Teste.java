package br.com.lucasatolini.vendas.online;

import br.com.lucasatolini.vendas.online.domain.Cliente;
import br.com.lucasatolini.vendas.online.repository.IClienteRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

@DataMongoTest
public class Teste {

    @Autowired
    IClienteRepository repository;

    @Test
    public void teste() {
        Cliente c = new Cliente();
        c.setNome("Lucas");
        c.setCpf(100_100_100_10L);
        this.repository.insert(c);
    }
}
