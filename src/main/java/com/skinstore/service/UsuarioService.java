package com.skinstore.service;

import java.util.List;

import com.skinstore.dto.UsuarioDTO;
import com.skinstore.dto.UsuarioResponseDTO;

import jakarta.validation.Valid;

public interface UsuarioService {
    public UsuarioResponseDTO insert(@Valid UsuarioDTO dto);
    public UsuarioResponseDTO update(UsuarioDTO dto, Long id);
    public void delete(Long id);
    public UsuarioResponseDTO findById(Long id);
    public UsuarioResponseDTO findByLoginAndSenha(String login, String senha);
    public UsuarioResponseDTO findByLogin(String login);
    public List<UsuarioResponseDTO> findByAll(); 
    public UsuarioResponseDTO updateSenha( String login, String senhaString);
}