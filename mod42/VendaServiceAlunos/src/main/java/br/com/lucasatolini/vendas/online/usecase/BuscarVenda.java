package br.com.lucasatolini.vendas.online.usecase;

import br.com.lucasatolini.vendas.online.domain.Venda;
import br.com.lucasatolini.vendas.online.errorhandler.EntityNotFoundException;
import br.com.lucasatolini.vendas.online.repository.IVendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BuscarVenda {
    private IVendaRepository vendaRepository;

    @Autowired
    public BuscarVenda(IVendaRepository produtoRepository) {
        this.vendaRepository = produtoRepository;
    }

    public Page<Venda> buscar(Pageable pageable) {
        return vendaRepository.findAll(pageable);
    }

    public Venda buscarPorCodigo(String codigo) {
        return vendaRepository.findByCodigo(codigo)
                .orElseThrow(() -> new EntityNotFoundException("codigo", codigo));
    }
}
