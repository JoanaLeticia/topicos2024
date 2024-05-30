package com.skinstore.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.skinstore.dto.ItemPedidoDTO;
import com.skinstore.dto.PedidoDTO;
import com.skinstore.dto.PedidoResponseDTO;
import com.skinstore.model.ItemPedido;
import com.skinstore.model.Pedido;
import com.skinstore.model.Produto;
import com.skinstore.repository.ClienteRepository;
import com.skinstore.repository.PedidoRepository;
import com.skinstore.repository.ProdutoRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class PedidoServiceImpl implements PedidoService {
    @Inject
    public PedidoRepository pedidoRepository;

    @Inject
    public ProdutoRepository produtoRepository;

    @Inject
    public ClienteRepository clienteRepository;

    @Override
    @Transactional
    public PedidoResponseDTO create(@Valid PedidoDTO dto) {
        Pedido pedido = new Pedido();
        pedido.setData(LocalDateTime.now());
        Double total = 0.0;
        for (ItemPedidoDTO itemPedidoDTO : dto.itens()) {
            total += (itemPedidoDTO.valor() * itemPedidoDTO.quantidade());
        }
        pedido.setTotal(total);

        List<ItemPedido> itens = new ArrayList<ItemPedido>();
        for (ItemPedidoDTO itemPedidoDTO : dto.itens()) {
            ItemPedido item = new ItemPedido();
            item.setQuantidade(itemPedidoDTO.quantidade());
            Produto produto = produtoRepository.findById(itemPedidoDTO.idProduto());
            if (produto == null) {
                throw new IllegalArgumentException("Não foi possível encontrar esse produto.");
            }
            item.setValor(produto.getValor().doubleValue());
            item.setProduto(produto);

            itens.add(item);

            // Atualizando estoque
            produto.setQuantEstoque(produto.getQuantEstoque() - 1);
        }

        pedido.setItens(itens);
        pedido.setCliente(clienteRepository.findById(dto.idCliente()));

        pedidoRepository.persist(pedido);

        return PedidoResponseDTO.valueOf(pedido);
    }

    @Override
    public PedidoResponseDTO findById(Long id) {
        Pedido pedido = pedidoRepository.findById(id);
        if (pedido != null)
            return PedidoResponseDTO.valueOf(pedido);
        return null;
    }

    @Override
    public List<PedidoResponseDTO> findAll() {
        return pedidoRepository.listAll()
                            .stream()
                            .map(e -> PedidoResponseDTO.valueOf(e)).toList();
    }

    @Override
    public List<PedidoResponseDTO> findByCliente (Long idCliente) {
        return pedidoRepository.findByCliente(idCliente).stream()
        .map(e -> PedidoResponseDTO.valueOf(e)).toList();
    }

    @Override
    public void delete(Long id) {
        Pedido pedido = pedidoRepository.findById(id);
        if (pedido == null) {
            throw new NotFoundException();
        }

        pedidoRepository.delete(pedido);
    }
}
