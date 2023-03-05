package br.com.lucasatolini.vendas.online.usecase;

import br.com.lucasatolini.vendas.online.domain.Produto;
import br.com.lucasatolini.vendas.online.domain.Produto.Status;
import br.com.lucasatolini.vendas.online.errorhandler.EntityNotFoundException;
import br.com.lucasatolini.vendas.online.repository.IProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BuscarProduto {
    private IProdutoRepository produtoRepository;

    @Autowired
    public BuscarProduto(IProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public Page<Produto> buscar(Pageable pageable) {
        return produtoRepository.findAll(pageable);
    }

    public Page<Produto> buscar(Pageable pageable, Status status) {
        return produtoRepository.findAllByStatus(pageable, status);
    }

    public Produto buscarPorCodigo(String codigo) {
        return produtoRepository.findByCodigo(codigo)
                .orElseThrow(() -> new EntityNotFoundException("codigo", codigo));
    }
}
