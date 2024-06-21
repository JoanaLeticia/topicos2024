package com.skinstore.service;

import com.skinstore.dto.UsuarioDTO;
import com.skinstore.dto.UsuarioResponseDTO;
import com.skinstore.model.Pessoa;
import com.skinstore.model.Usuario;
import com.skinstore.repository.PedidoRepository;
import com.skinstore.repository.PessoaRepository;
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

    @Inject
    PessoaRepository pessoaRepository;

    @Override
    @Transactional
    public UsuarioResponseDTO insert(@Valid UsuarioDTO dto) {

        if (repository.findByLogin(dto.login()) != null) {
            throw new ValidationException("login", "Login já existe.");
        }

        Usuario novoUsuario = new Usuario();
        novoUsuario.setLogin(dto.login());
        novoUsuario.setSenha(dto.senha());
        novoUsuario.setPerfil(dto.idPerfil());

        Pessoa novaPessoa = new Pessoa();
        novaPessoa.setNome(dto.nome());
        novaPessoa.setCpf(dto.cpf());
        novaPessoa.setUsuario(novoUsuario);

        repository.persist(novoUsuario);

        return UsuarioResponseDTO.valueOf(novaPessoa);
    }

    @Override
    @Transactional
    public UsuarioResponseDTO update(UsuarioDTO dto, Long id) {

        Usuario attUsuario = new Usuario();
        attUsuario.setLogin(dto.login());
        attUsuario.setSenha(dto.senha());
        attUsuario.setPerfil(dto.idPerfil());

        Pessoa attPessoa = new Pessoa();
        attPessoa.setNome(dto.nome());
        attPessoa.setCpf(dto.cpf());
        attPessoa.setUsuario(attUsuario);

        repository.persist(attUsuario);

        return UsuarioResponseDTO.valueOf(attPessoa);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public UsuarioResponseDTO findById(Long id) {
        return UsuarioResponseDTO.valueOf(pessoaRepository.findById(id));
    }
    
}
