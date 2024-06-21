package com.skinstore.service;

import java.util.List;

import com.skinstore.dto.AdministradorDTO;
import com.skinstore.dto.AdministradorResponseDTO;
import com.skinstore.dto.UsuarioResponseDTO;

import jakarta.validation.Valid;

public interface AdministradorService {
    public AdministradorResponseDTO insert(@Valid AdministradorDTO dto);
    public AdministradorResponseDTO update(AdministradorDTO dto, Long id);
    public void delete(Long id);
    public AdministradorResponseDTO findById(Long id);
    public List<AdministradorResponseDTO> findByNome(String nome);
    public List<AdministradorResponseDTO> findAll();
    public UsuarioResponseDTO login(String login, String senha);
}
