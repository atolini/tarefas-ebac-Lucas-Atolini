package br.com.lucasatolini.vendas.online.usecase;

import br.com.lucasatolini.vendas.online.domain.Produto;
import br.com.lucasatolini.vendas.online.domain.Produto.Status;
import br.com.lucasatolini.vendas.online.errorhandler.EntityNotFoundException;
import br.com.lucasatolini.vendas.online.repository.IProdutoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastrarProduto {
    private IProdutoRepository produtoRepository;

    @Autowired
    public CadastrarProduto(IProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public Produto cadastrar(@Valid Produto produto) {
        produto.setStatus(Status.ATIVO);
        return this.produtoRepository.insert(produto);
    }

    public Produto atualizar(@Valid Produto produto) {
        return this.produtoRepository.save(produto);
    }

    public void remover(String id) {
        Produto prod = produtoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("id", id));
        prod.setStatus(Status.INATIVO);
        this.produtoRepository.save(prod);
    }
}
