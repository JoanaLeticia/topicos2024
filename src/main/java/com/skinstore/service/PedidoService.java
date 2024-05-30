package com.skinstore.service;

import java.util.List;

import com.skinstore.dto.PedidoDTO;
import com.skinstore.dto.PedidoResponseDTO;
import jakarta.validation.Valid;

public interface PedidoService {
    public PedidoResponseDTO create(@Valid PedidoDTO dto);
    public void delete(Long id);
    public PedidoResponseDTO findById(Long id);
    public List<PedidoResponseDTO> findAll();
    public List<PedidoResponseDTO> findByCliente(Long idCliente);
}
