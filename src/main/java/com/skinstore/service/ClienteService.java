package com.skinstore.service;

import java.util.List;

import com.skinstore.dto.ClienteDTO;
import com.skinstore.dto.ClienteResponseDTO;
import com.skinstore.dto.UsuarioResponseDTO;

import jakarta.validation.Valid;

public interface ClienteService {
    public ClienteResponseDTO insert(@Valid ClienteDTO dto);
    public ClienteResponseDTO update(ClienteDTO dto, Long id);
    public void delete(Long id);
    public ClienteResponseDTO findById(Long id);
    public List<ClienteResponseDTO> findByNome(String nome);
    public List<ClienteResponseDTO> findByAll();
    public UsuarioResponseDTO login(String login, String senha);
    public ClienteResponseDTO findByLogin(String login);
}