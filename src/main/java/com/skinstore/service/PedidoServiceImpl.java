package com.skinstore.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.skinstore.dto.ItemPedidoDTO;
import com.skinstore.dto.ItemPedidoResponseDTO;
import com.skinstore.dto.PedidoDTO;
import com.skinstore.dto.PedidoResponseDTO;
import com.skinstore.model.Cliente;
import com.skinstore.model.Endereco;
import com.skinstore.model.ItemPedido;
import com.skinstore.model.Pedido;
import com.skinstore.model.Produto;
import com.skinstore.repository.ClienteRepository;
import com.skinstore.repository.EnderecoRepository;
import com.skinstore.repository.ItemPedidoRepository;
import com.skinstore.repository.PagamentoRepository;
import com.skinstore.repository.PedidoRepository;
import com.skinstore.repository.ProdutoRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class PedidoServiceImpl implements PedidoService {
    @Inject
    ProdutoRepository produtoRepository;

    @Inject
    ClienteRepository clienteRepository;

    @Inject
    ItemPedidoRepository itemPedidoRepository;

    @Inject
    PedidoRepository pedidoRepository;

    @Inject
    EnderecoRepository enderecoRepository;

    @Inject
    PagamentoRepository pagamentoRepository;

    @Override
    @Transactional
    public PedidoResponseDTO insert(PedidoDTO dto, String login) {
        Pedido pedido = new Pedido();
        pedido.setDataHora(LocalDateTime.now());

        Double total = 0.0;
        for (ItemPedidoDTO itemDTO : dto.itens()) {
            total += (itemDTO.valor() * itemDTO.quantidade());
        }
        pedido.setValorTotal(total);

        pedido.setItens(new ArrayList<ItemPedido>());
        for (ItemPedidoDTO itemDTO : dto.itens()) {
            ItemPedido item = new ItemPedido();
            item.setValor(itemDTO.valor());
            item.setQuantidade(itemDTO.quantidade());
            Produto produto = produtoRepository.findById(itemDTO.idProduto());
            if (produto == null){
                throw new IllegalArgumentException("Não existe esse produto!");
            }
            item.setProduto(produto);

            // atualizado o estoque
            produto.setQuantEstoque(produto.getQuantEstoque() - item.getQuantidade());

            pedido.getItens().add(item);
        }
        
        Endereco endereco = enderecoRepository.findById(dto.idEndereco());
        pedido.setEndereco(endereco);

        pedido.setCliente((Cliente) clienteRepository.findByLogin(login));

        pedidoRepository.persist(pedido);

        return PedidoResponseDTO.valueOf(pedido);
    }

    @Override
    public PedidoResponseDTO findById(Long id) {
        return PedidoResponseDTO.valueOf(pedidoRepository.findById(id));
    }

    @Override
    public List<PedidoResponseDTO> findByAll() {
        return pedidoRepository.listAll().stream()
                .map(e -> PedidoResponseDTO.valueOf(e)).toList();
    }

    @Override
    public List<PedidoResponseDTO> findByAll(String login) {
        return pedidoRepository.listAll().stream()
                .map(e -> PedidoResponseDTO.valueOf(e)).toList();
    }

    @Override
    public void delete(Long id) {
        Pedido pedido = pedidoRepository.findById(id);
        if (pedido == null) {
            throw new NotFoundException();
        }

        for (ItemPedido item : pedido.getItens()) {
            itemPedidoRepository.delete(item);
        }

        pedidoRepository.delete(pedido);
    }

    @Override
    public List<PedidoResponseDTO> pedidosUsuarioLogado(Cliente cliente) {
        Cliente usuario = clienteRepository.findByLogin(cliente.getPessoa().getUsuario().getLogin());
        List<Pedido> pedidos = pedidoRepository.findByUsuario(usuario);
        return pedidos.stream().map(p -> PedidoResponseDTO.valueOf(p)).collect(Collectors.toList());
    }

    public List<ItemPedidoResponseDTO> findItensByUsuario(Cliente cliente) {
        List<Pedido> pedidos = pedidoRepository.findByUsuario(cliente);
        List<ItemPedido> itens = new ArrayList<>();
    
        for (Pedido pedido : pedidos) {
            itens.addAll(pedido.getItens());
        }
    
        return itens.stream()
                .map(i -> ItemPedidoResponseDTO.valueOf(i))
                .collect(Collectors.toList());
    }
}