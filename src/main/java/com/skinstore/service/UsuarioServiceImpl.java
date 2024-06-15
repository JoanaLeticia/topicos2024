package com.skinstore.service;

import java.util.List;

import com.skinstore.dto.UsuarioDTO;
import com.skinstore.dto.UsuarioResponseDTO;
import com.skinstore.model.Perfil;
import com.skinstore.model.Usuario;
import com.skinstore.repository.PedidoRepository;
import com.skinstore.repository.UsuarioRepository;
import com.skinstore.validation.ValidationException;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@ApplicationScoped
public class UsuarioServiceImpl implements UsuarioService {

    @Inject
    UsuarioRepository repository;

    @Inject
    PedidoRepository pedidoRepository;

    @Override
    @Transactional
    public UsuarioResponseDTO insert(@Valid UsuarioDTO dto) {

        if (repository.findByLogin(dto.login()) != null) {
            throw new ValidationException("login", "Login já existe.");
        }

        Usuario usuario = new Usuario();
        usuario.setLogin(dto.login());
        usuario.setSenha(dto.senha());
        usuario.setPerfil(Perfil.valueOf(dto.idPerfil()));

        repository.persist(usuario);

        return UsuarioResponseDTO.valueOf(usuario);
    }

    @Override
    @Transactional
    public UsuarioResponseDTO update(UsuarioDTO dto, Long id) {
        Usuario usuarioAtt = repository.findById(id);
        usuarioAtt.setLogin(dto.login());
        usuarioAtt.setSenha(dto.senha());
        usuarioAtt.setPerfil(Perfil.valueOf(dto.idPerfil()));

        repository.persist(usuarioAtt);

        return UsuarioResponseDTO.valueOf(usuarioAtt);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public UsuarioResponseDTO findById(Long id) {
        return UsuarioResponseDTO.valueOf(repository.findById(id));
    }

    @Override
    public List<UsuarioResponseDTO> findByAll() {
        return repository.listAll().stream()
                .map(e -> UsuarioResponseDTO.valueOf(e)).toList();
    }

    @Override
    public UsuarioResponseDTO findByLoginAndSenha(String login, String senha) {
        Usuario usuario = repository.findByLoginAndSenha(login, senha);
        if (usuario == null)
            throw new ValidationException("login", "Login ou senha inválido");

        return UsuarioResponseDTO.valueOf(usuario);
    }

    @Override
    public UsuarioResponseDTO findByLogin(String login) {
        Usuario usuario = repository.findByLogin(login);
        if (usuario == null)
            throw new ValidationException("login", "Login inválido");

        return UsuarioResponseDTO.valueOf(usuario);
    }

    @Override
    public UsuarioResponseDTO updateSenha(String login, String senha) {
        Usuario usuario = repository.findByLogin(login);
        usuario.setSenha(senha);
        repository.persist(usuario);
        return UsuarioResponseDTO.valueOf(usuario);
    }

}