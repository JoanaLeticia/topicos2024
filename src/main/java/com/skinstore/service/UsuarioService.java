package com.skinstore.service;

import java.util.List;

import com.skinstore.dto.UsuarioDTO;
import com.skinstore.dto.UsuarioResponseDTO;

import jakarta.validation.Valid;

public interface UsuarioService {
    public UsuarioResponseDTO create(@Valid UsuarioDTO dto);
    public void update(Long id, UsuarioDTO dto);
    public void delete(Long id);
    public UsuarioResponseDTO findById(Long id);
    public List<UsuarioResponseDTO> findAll();
    public List<UsuarioResponseDTO> findByLogin(String login);
}
