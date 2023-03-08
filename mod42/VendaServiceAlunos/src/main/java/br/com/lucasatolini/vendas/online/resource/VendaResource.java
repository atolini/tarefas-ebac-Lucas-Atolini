 package br.com.lucasatolini.vendas.online.resource;

import br.com.lucasatolini.vendas.online.domain.Venda;
import br.com.lucasatolini.vendas.online.dto.VendaDTO;
import br.com.lucasatolini.vendas.online.usecase.BuscarVenda;
import br.com.lucasatolini.vendas.online.usecase.CadastrarVenda;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

 @RestController
@RequestMapping(value = "/venda")
public class VendaResource {
    private BuscarVenda buscarVenda;

    private CadastrarVenda cadastrarVenda;

    @Autowired
    public VendaResource(BuscarVenda buscarVenda,
                         CadastrarVenda cadastrarVenda) {
        this.buscarVenda = buscarVenda;
        this.cadastrarVenda = cadastrarVenda;
    }

    @GetMapping
    public ResponseEntity<Page<Venda>> buscar(Pageable pageable) {
        return ResponseEntity.ok(buscarVenda.buscar(pageable));
    }

    @PostMapping
    public ResponseEntity<Venda> cadastrar(@RequestBody @Valid VendaDTO vendaDTO) {
        return ResponseEntity.ok(cadastrarVenda.cadastrar(vendaDTO));
    }

     @PutMapping("/{id}/{codigoProduto}/{quantidade}/addProduto")
     public ResponseEntity<Venda> adicionarProduto(
             @PathVariable(name = "id", required = true) String id,
             @PathVariable(name = "codigoProduto", required = true) String codigoProduto,
             @PathVariable(name = "quantidade", required = true) Integer quantidade) {
         return ResponseEntity.ok(cadastrarVenda.adicionarProduto(id, codigoProduto, quantidade));
     }
}
