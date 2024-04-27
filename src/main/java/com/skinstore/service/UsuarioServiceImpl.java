package com.skinstore.service;

import java.util.List;

import com.skinstore.dto.UsuarioDTO;
import com.skinstore.dto.UsuarioResponseDTO;
import com.skinstore.model.Usuario;
import com.skinstore.repository.UsuarioRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@ApplicationScoped
public class UsuarioServiceImpl implements UsuarioService{
    @Inject
    public UsuarioRepository usuarioRepository;

    @Override
    @Transactional
    public UsuarioResponseDTO create(@Valid UsuarioDTO dto) {
        Usuario user = new Usuario();
        user.setLogin(dto.login());
        user.setSenha(dto.senha());

        usuarioRepository.persist(user);
        return UsuarioResponseDTO.valueOf(user);
    }

    @Override
    @Transactional
    public void update(Long id, UsuarioDTO dto) {
        Usuario userUpdate = usuarioRepository.findById(id);

        userUpdate.setLogin(dto.login());
        userUpdate.setSenha(dto.senha());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        usuarioRepository.deleteById(id);
    }

    @Override
    public UsuarioResponseDTO findById(Long id) {
        return UsuarioResponseDTO.valueOf(usuarioRepository.findById(id));
    }

    @Override
    public List<UsuarioResponseDTO> findAll() {
        return usuarioRepository
                .listAll()
                .stream()
                .map(e -> UsuarioResponseDTO.valueOf(e)).toList();
    }

    @Override
    public List<UsuarioResponseDTO> findByLogin(String login) {
        return usuarioRepository.findByLogin(login).stream()
                .map(e -> UsuarioResponseDTO.valueOf(e)).toList();
    }
}
