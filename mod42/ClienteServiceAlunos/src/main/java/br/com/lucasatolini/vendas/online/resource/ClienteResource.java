package br.com.lucasatolini.vendas.online.resource;

import br.com.lucasatolini.vendas.online.usecase.BuscarCliente;
import br.com.lucasatolini.vendas.online.usecase.CadastrarCliente;
import br.com.lucasatolini.vendas.online.domain.Cliente;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/cliente")
public class ClienteResource {
    private BuscarCliente buscarCliente;

    private CadastrarCliente cadastrarCliente;

    @Autowired
    public ClienteResource(BuscarCliente buscarCliente, CadastrarCliente cadastrarCliente) {
        this.buscarCliente = buscarCliente;
        this.cadastrarCliente = cadastrarCliente;
    }

    @GetMapping
    public ResponseEntity<Page<Cliente>> buscar(Pageable pageable) {
        return ResponseEntity.ok(buscarCliente.buscar(pageable));
    }

    @PostMapping
    public ResponseEntity<Cliente> cadastrar(@RequestBody @Valid Cliente cliente) {
        return ResponseEntity.ok(cadastrarCliente.cadastrar(cliente));
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Busca um cliente pelo id")
    public ResponseEntity<Cliente> buscarPorId(@PathVariable(value = "id", required = true) String id) {
        return ResponseEntity.ok(buscarCliente.buscarPorId(id));
    }

    @GetMapping(value = "isCadastrado/{id}")
    public ResponseEntity<Boolean> isCadastrado(@PathVariable(value = "id", required = true) String id) {
        return ResponseEntity.ok(buscarCliente.isCadastrado(id));
    }

    @GetMapping(value = "/cpf/{cpf}")
    @Operation(summary = "Busca um cliente pelo cpf")
    public ResponseEntity<Cliente> buscarPorCpf(@PathVariable(value = "cpf", required = true) Long cpf) {
        return ResponseEntity.ok(buscarCliente.buscarPorCpf(cpf));
    }

    @PutMapping
    @Operation(summary = "Atualiza um cliente")
    public ResponseEntity<Cliente> atualizar(@RequestBody @Valid Cliente cliente) {
        return ResponseEntity.ok(cadastrarCliente.atualizar(cliente));
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Remove um cliente pelo seu identificador único")
    public ResponseEntity<String> remover(@PathVariable(value = "id") String id) {
        cadastrarCliente.remover(id);
        return ResponseEntity.ok("Removido com sucesso");
    }
}
