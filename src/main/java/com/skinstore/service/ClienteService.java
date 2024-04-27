package com.skinstore.service;

import java.util.List;

import com.skinstore.dto.ClienteDTO;
import com.skinstore.dto.ClienteResponseDTO;

import jakarta.validation.Valid;

public interface ClienteService {
    public ClienteResponseDTO create(@Valid ClienteDTO dto);
    public void update(Long id, ClienteDTO dto);
    public void delete(Long id);
    public ClienteResponseDTO findById(Long id);
    public List<ClienteResponseDTO> findAll();
    public List<ClienteResponseDTO> findByNome(String nome);
}
