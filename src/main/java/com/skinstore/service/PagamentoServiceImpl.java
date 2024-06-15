package com.skinstore.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.skinstore.dto.BoletoBancarioDTO;
import com.skinstore.dto.CartaoCreditoDTO;
import com.skinstore.dto.PagamentoDTO;
import com.skinstore.dto.PagamentoResponseDTO;
import com.skinstore.dto.PixDTO;
import com.skinstore.model.BoletoBancario;
import com.skinstore.model.CartaoCredito;
import com.skinstore.model.Cliente;
import com.skinstore.model.Pagamento;
import com.skinstore.model.Pedido;
import com.skinstore.model.Pix;
import com.skinstore.model.StatusPagamento;
import com.skinstore.repository.ClienteRepository;
import com.skinstore.repository.PagamentoRepository;
import com.skinstore.repository.PedidoRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class PagamentoServiceImpl implements PagamentoService {

    @Inject
    PagamentoRepository repository;

    @Inject
    ClienteRepository clienteRepository;

    @Inject
    PedidoRepository pedidoRepository;

    @Override
    @Transactional
    public PagamentoResponseDTO insert(PagamentoDTO dto, String login) {
        Pagamento pagamentoExistente = new Pagamento();
        pagamentoExistente.setTipo(dto.tipo());

        if (dto.pix() != null && !dto.pix().isEmpty()) {
            pagamentoExistente.setPix(new ArrayList<Pix>());
            for (PixDTO p : dto.pix()) {
                Pix pix = new Pix();
                pix.setChavePix(p.chavePix());
            }
        }

        if (dto.boletoBancario() != null && !dto.boletoBancario().isEmpty()) {
            pagamentoExistente.setBoletoBancario(new ArrayList<BoletoBancario>());
            for (BoletoBancarioDTO boleto : dto.boletoBancario()) {
                BoletoBancario bancario = new BoletoBancario();
                bancario.setBanco(boleto.banco());
                bancario.setNumeroBoleto(boleto.numeroBoleto());
                bancario.setDataVencimento(boleto.dataVencimento());
            }
        }

        if (dto.cartaoCredito() != null && !dto.cartaoCredito().isEmpty()) {
            pagamentoExistente.setCartaoCredito(new ArrayList<CartaoCredito>());
            for (CartaoCreditoDTO cartao : dto.cartaoCredito()) {
                CartaoCredito credito = new CartaoCredito();
                credito.setBandeira(cartao.bandeira());
                credito.setCodigoSeguranca(cartao.codigoSeguranca());
                credito.setDataVencimento(cartao.dataVencimento());
                credito.setNumeroCartao(cartao.numeroCartao());
            }
        }

        // Obter o cliente do token
        Cliente cliente = clienteRepository.findByLogin(login);
        // Retorne o último pedido do cliente
        Pedido ultimoPedido = pedidoRepository.obterUltimoPedidoDoCliente(cliente.getId());

        // Atribuindo o ultimo pedido ao pagmento
        pagamentoExistente.setPedido(ultimoPedido);

        // Atribuindo o valor do ultimo pedido ao pagmento
        pagamentoExistente.setValor(ultimoPedido.getValorTotal());

        pagamentoExistente.setStatusPagamento(StatusPagamento.APROVADO);

        repository.persist(pagamentoExistente);
        return PagamentoResponseDTO.valueOf(pagamentoExistente);
    }

    public boolean pedidoJaPago(String login) {
        // Obter o cliente do token
        Cliente cliente = clienteRepository.findByLogin(login);
    
        // Retorne o último pedido do cliente
        Pedido ultimoPedido = pedidoRepository.obterUltimoPedidoDoCliente(cliente.getId());
    
        // Lógica para verificar se o pedido já foi pago
        return repository.existsByPedidoId(ultimoPedido.getId());
    }

    @Override
    @Transactional
    public PagamentoResponseDTO update(PagamentoDTO dto, Long id) {

        Pagamento pagamentoExistente = repository.findById(id);

        pagamentoExistente.setTipo(dto.tipo());

        // Atualizar a lista de Pix
        if (dto.pix() != null && !dto.pix().isEmpty()) {
            pagamentoExistente.getPix().clear(); // Limpar a lista existente
            for (PixDTO p : dto.pix()) {
                Pix pix = new Pix();
                pix.setChavePix(p.chavePix());
                pagamentoExistente.getPix().add(pix); // Adicionar o novo Pix à lista

                // Persistir a entidade Pix
                repository.persist(pagamentoExistente);
            }
        }

        // Atualizar a lista de BoletoBancario
        if (dto.boletoBancario() != null && !dto.boletoBancario().isEmpty()) {
            pagamentoExistente.getBoletoBancario().clear(); // Limpar a lista existente
            for (BoletoBancarioDTO boleto : dto.boletoBancario()) {
                BoletoBancario bancario = new BoletoBancario();
                bancario.setBanco(boleto.banco());
                bancario.setNumeroBoleto(boleto.numeroBoleto());
                bancario.setDataVencimento(boleto.dataVencimento());
                pagamentoExistente.getBoletoBancario().add(bancario); // Adicionar o novo BoletoBancario à lista

                // Persistir a entidade BoletoBancario
                repository.persist(pagamentoExistente);
            }
        }

        // Atualizar a lista de CartaoCredito
        if (dto.cartaoCredito() != null && !dto.cartaoCredito().isEmpty()) {
            pagamentoExistente.getCartaoCredito().clear(); // Limpar a lista existente
            for (CartaoCreditoDTO cartao : dto.cartaoCredito()) {
                CartaoCredito credito = new CartaoCredito();
                credito.setBandeira(cartao.bandeira());
                credito.setCodigoSeguranca(cartao.codigoSeguranca());
                credito.setDataVencimento(cartao.dataVencimento());
                credito.setNumeroCartao(cartao.numeroCartao());
                pagamentoExistente.getCartaoCredito().add(credito); // Adicionar o novo CartaoCredito à lista

                // Persistir a entidade CartaoCredito
                repository.persist(pagamentoExistente);
            }
        }

        repository.persist(pagamentoExistente);

        return PagamentoResponseDTO.valueOf(pagamentoExistente);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!repository.deleteById(id))
            throw new NotFoundException();
    }

    @Override
    public PagamentoResponseDTO findById(Long id) {
        Pagamento boleto = repository.findById(id);
        if (boleto == null) {
            throw new EntityNotFoundException("Pagamento não encontrado com ID: " + id);
        }
        return PagamentoResponseDTO.valueOf(boleto);
    }

    @Override
    public List<PagamentoResponseDTO> findByAll() {
        return repository.listAll().stream()
                .map(e -> PagamentoResponseDTO.valueOf(e)).toList();
    }

    @Override
    public List<PagamentoResponseDTO> findByTipo(String tipo) {
        List<Pagamento> list = repository.findByTipo(tipo);
        return list.stream().map(u -> PagamentoResponseDTO.valueOf(u)).collect(Collectors.toList());
    }

}
