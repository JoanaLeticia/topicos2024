package com.skinstore.service;

import java.util.List;

import com.skinstore.dto.CartaoCreditoDTO;
import com.skinstore.dto.CartaoCreditoResponseDTO;

public interface CartaoCreditoService {
    public CartaoCreditoResponseDTO insert(CartaoCreditoDTO dto);
    public CartaoCreditoResponseDTO update(CartaoCreditoDTO dto, Long id);
    public void delete(Long id);
    public CartaoCreditoResponseDTO findById(Long id);
    public List<CartaoCreditoResponseDTO> findByBandeira(String bandeira);
    public List<CartaoCreditoResponseDTO> findByAll(); 
}

