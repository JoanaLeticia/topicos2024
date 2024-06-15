package com.skinstore.service;

import java.util.List;

import com.skinstore.dto.PedidoDTO;
import com.skinstore.dto.PedidoResponseDTO;
import com.skinstore.model.Cliente;
import com.skinstore.dto.ItemPedidoResponseDTO;

public interface PedidoService {
    public PedidoResponseDTO insert(PedidoDTO dto, String login);
    public void delete(Long id);
    public PedidoResponseDTO findById(Long id);
    public List<PedidoResponseDTO> findByAll();
    public List<PedidoResponseDTO> findByAll(String login);
    public List<PedidoResponseDTO> pedidosUsuarioLogado(Cliente cliente);
    public List<ItemPedidoResponseDTO> findItensByUsuario(Cliente cliente);
}