package br.com.lucasatolini.vendas.online.resource;

import br.com.lucasatolini.vendas.online.domain.Produto;
import br.com.lucasatolini.vendas.online.domain.Produto.Status;
import br.com.lucasatolini.vendas.online.usecase.BuscarProduto;
import br.com.lucasatolini.vendas.online.usecase.CadastrarProduto;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/produto")
public class ProdutoResource {
    private BuscarProduto buscarProduto;

    private CadastrarProduto cadastrarProduto;

    @Autowired
    public ProdutoResource(BuscarProduto buscarProduto,
                           CadastrarProduto cadastrarProduto) {
        this.buscarProduto = buscarProduto;
        this.cadastrarProduto = cadastrarProduto;
    }

    @GetMapping
    @Operation(summary = "Busca uma lista paginada de produtos.")
    public ResponseEntity<Page<Produto>> buscar(Pageable pageable) {
        return ResponseEntity.ok(this.buscarProduto.buscar(pageable));
    }

    @GetMapping(value = "/status/{status}")
    @Operation(summary = "Busca uma lista paginada de produtos por status")
    public ResponseEntity<Page<Produto>> buscarPorStatus(Pageable pageable,
                                                         @PathVariable(value = "status", required = true) Status status) {
        return ResponseEntity.ok(buscarProduto.buscar(pageable, status));
    }

    @GetMapping(value = "/{codigo}")
    @Operation(summary = "Busca um produto pelo codigo")
    public ResponseEntity<Produto> buscarPorCodigo(String codigo) {
        return ResponseEntity.ok(buscarProduto.buscarPorCodigo(codigo));
    }

    @PostMapping
    @Operation(summary = "Cadastrar um produto")
    public ResponseEntity<Produto> cadastrar(@RequestBody @Valid Produto produto) {
        return ResponseEntity.ok(cadastrarProduto.cadastrar(produto));
    }

    @PutMapping
    @Operation(summary = "Atualiza um produto")
    public ResponseEntity<Produto> atualizar(@RequestBody @Valid Produto produto) {
        return ResponseEntity.ok(cadastrarProduto.atualizar(produto));
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Remove um produto pelo seu identificador único")
    public ResponseEntity<String> remover(@PathVariable(value = "id") String id) {
        cadastrarProduto.remover(id);
        return ResponseEntity.ok("Removido com sucesso");
    }
}
