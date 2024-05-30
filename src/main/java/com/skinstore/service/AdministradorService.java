package com.skinstore.service;

import java.util.List;

import com.skinstore.dto.AdministradorDTO;
import com.skinstore.dto.AdministradorResponseDTO;
import com.skinstore.dto.UsuarioResponseDTO;

import jakarta.validation.Valid;

public interface AdministradorService {
    public AdministradorResponseDTO create(@Valid AdministradorDTO dto);
    public void update(Long id, AdministradorDTO dto);
    public void delete(Long id);
    public AdministradorResponseDTO findById(Long id);
    public List<AdministradorResponseDTO> findAll();
    public List<AdministradorResponseDTO> findByNome(String nome);
    public UsuarioResponseDTO login(String login, String senha);
}
