package br.com.lucasatolini.vendas.online.usecase;

import br.com.lucasatolini.vendas.online.domain.Produto;
import br.com.lucasatolini.vendas.online.domain.Venda;
import br.com.lucasatolini.vendas.online.domain.Venda.Status;
import br.com.lucasatolini.vendas.online.dto.VendaDTO;
import br.com.lucasatolini.vendas.online.errorhandler.EntityNotFoundException;
import br.com.lucasatolini.vendas.online.repository.IVendaRepository;
import br.com.lucasatolini.vendas.online.service.ClienteService;
import br.com.lucasatolini.vendas.online.service.IProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashSet;

@Service
public class CadastrarVenda {
    private IVendaRepository vendaRepository;

    private IProdutoService produtoService;

    private ClienteService clienteService;

    @Autowired
    public CadastrarVenda(IVendaRepository vendaRepository,
                         IProdutoService produtoService,
                         ClienteService clienteService) {
        this.vendaRepository = vendaRepository;
        this.produtoService = produtoService;
        this.clienteService = clienteService;
    }

    public Venda cadastrar(@Valid VendaDTO vendaDTO) {
        Venda venda = convertToDomain(vendaDTO, Venda.Status.INICIADA);
        validarCliente(venda.getClienteId());
        venda.recalcularValorTotalVenda();
        return this.vendaRepository.insert(venda);
    }

    private void validarCliente(String clienteId) {
        Boolean isCadastrado =
                this.clienteService.isClienteCadastrado(clienteId);
        if (!isCadastrado) {
            new EntityNotFoundException("clienteId", clienteId);
        }
    }

    private Venda convertToDomain(@Valid VendaDTO vendaDTO, Status status) {
        return Venda.builder()
                .clienteId(vendaDTO.getClienteId())
                .codigo(vendaDTO.getCodigo())
                .dataVenda(vendaDTO.getDataVenda())
                .status(status)
                .valorTotal(BigDecimal.ZERO)
                .produtos(new HashSet<>())
                .build();
    }

    public Venda atualizar(@Valid Venda venda) {
        return this.vendaRepository.save(venda);
    }

    public Venda finalizar(String id) {
        Venda venda = buscarVenda(id);
        venda.validarStatus();
        venda.setStatus(Status.CONCLUIDA);
        return this.vendaRepository.save(venda);
    }

    public Venda cancelar(String id) {
        Venda venda = buscarVenda(id);
        venda.validarStatus();
        venda.setStatus(Status.CANCELADA);
        return this.vendaRepository.save(venda);
    }

    public Venda adicionarProduto(String id, String codigoProduto, Integer quantidade) {
        Venda venda = buscarVenda(id);
        Produto produto = buscarProduto(codigoProduto);
        venda.validarStatus();
        venda.adicionarProduto(produto, quantidade);
        return this.vendaRepository.save(venda);
    }

    public Venda removerProduto(String id, String codigoProduto, Integer quantidade) {
        Venda venda = buscarVenda(id);
        Produto produto = buscarProduto(codigoProduto);
        venda.validarStatus();
        venda.removerProduto(produto, quantidade);
        return this.vendaRepository.save(venda);
    }

    private Venda buscarVenda(String id) {
        return vendaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("id", id));

    }

    private Produto buscarProduto(String codigoProduto) {
        Produto prod = produtoService.buscarProduto(codigoProduto);
        if (prod == null) {
            throw new EntityNotFoundException("codigo", codigoProduto);
        }
        return prod;
    }
}
