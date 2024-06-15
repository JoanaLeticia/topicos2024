package com.skinstore.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.skinstore.dto.CartaoCreditoDTO;
import com.skinstore.dto.CartaoCreditoResponseDTO;
import com.skinstore.model.CartaoCredito;
import com.skinstore.repository.CartaoCreditoRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class CartaoCreditoServiceImpl implements CartaoCreditoService {

    @Inject
    CartaoCreditoRepository repository;

    @Override
    @Transactional
    public CartaoCreditoResponseDTO insert(CartaoCreditoDTO dto) {
        CartaoCredito novoCartaoCredito = new CartaoCredito();
        novoCartaoCredito.setBandeira(dto.bandeira());
        novoCartaoCredito.setCodigoSeguranca(dto.codigoSeguranca());
        if (dto.dataVencimento().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("A data de vencimento não pode ser anterior ao dia atual");
        }
        novoCartaoCredito.setDataVencimento(dto.dataVencimento());
        novoCartaoCredito.setNumeroCartao(dto.numeroCartao());
        novoCartaoCredito.setQuantidadeParcelas(dto.quantParcelas());

        repository.persist(novoCartaoCredito);

        return CartaoCreditoResponseDTO.valueOf(novoCartaoCredito);
    }

    @Override
    @Transactional
    public CartaoCreditoResponseDTO update(CartaoCreditoDTO dto, Long id) {

        CartaoCredito cartaoExistente = repository.findById(id);
        cartaoExistente.setBandeira(dto.bandeira());
        cartaoExistente.setCodigoSeguranca(dto.codigoSeguranca());
        if (dto.dataVencimento().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("A data de vencimento não pode ser anterior ao dia atual");
        }
        cartaoExistente.setDataVencimento(dto.dataVencimento());
        cartaoExistente.setNumeroCartao(dto.numeroCartao());
        cartaoExistente.setQuantidadeParcelas(dto.quantParcelas());
        return CartaoCreditoResponseDTO.valueOf(cartaoExistente);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!repository.deleteById(id))
            throw new NotFoundException();
    }

    @Override
    public CartaoCreditoResponseDTO findById(Long id) {
        CartaoCredito boleto = repository.findById(id);
        if (boleto == null) {
            throw new EntityNotFoundException("Cartão de Credito não encontrado com ID: " + id);
        }
        return CartaoCreditoResponseDTO.valueOf(boleto);
    }

    @Override
    public List<CartaoCreditoResponseDTO> findByAll() {
        return repository.listAll().stream()
                .map(e -> CartaoCreditoResponseDTO.valueOf(e)).toList();
    }

    @Override
    public List<CartaoCreditoResponseDTO> findByBandeira(String bandeira) {
        List<CartaoCredito> list = repository.findByBandeira(bandeira);
        return list.stream().map(u -> CartaoCreditoResponseDTO.valueOf(u)).collect(Collectors.toList());
    }



}
