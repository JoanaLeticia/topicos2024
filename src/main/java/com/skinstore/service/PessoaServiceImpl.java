package com.skinstore.service;

import java.util.List;

import com.skinstore.dto.PessoaDTO;
import com.skinstore.dto.PessoaResponseDTO;
import com.skinstore.model.Perfil;
import com.skinstore.model.Pessoa;
import com.skinstore.model.Usuario;
import com.skinstore.repository.PedidoRepository;
import com.skinstore.repository.PessoaRepository;
import com.skinstore.validation.ValidationException;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@ApplicationScoped
public class PessoaServiceImpl implements PessoaService {
    @Inject
    PessoaRepository repository;

    @Inject
    PedidoRepository pedidoRepository;

    @Override
    @Transactional
    public PessoaResponseDTO insert(@Valid PessoaDTO dto) {

        if (repository.findByLogin(dto.email()) != null) {
            throw new ValidationException("login", "Login já existe.");
        }

        Usuario usuario = new Usuario();
        usuario.setLogin(dto.email());
        usuario.setSenha(dto.senha());
        usuario.setPerfil(Perfil.valueOf(dto.idPerfil()));

        Pessoa pessoa = new Pessoa();
        pessoa.setCpf(dto.cpf());
        pessoa.setNome(dto.nome());

        repository.persist(pessoa);

        return PessoaResponseDTO.valueOf(pessoa);
    }

    @Override
    @Transactional
    public PessoaResponseDTO update(Long id, PessoaDTO dto) {
        Pessoa pessoaAtt = repository.findById(id);
        pessoaAtt.setCpf(dto.cpf());
        pessoaAtt.setNome(dto.nome());
        pessoaAtt.getUsuario().setLogin(dto.email());
        pessoaAtt.getUsuario().setSenha(dto.senha());
        pessoaAtt.getUsuario().setPerfil(Perfil.valueOf(dto.idPerfil()));

        repository.persist(pessoaAtt);

        return PessoaResponseDTO.valueOf(pessoaAtt);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public PessoaResponseDTO findById(Long id) {
        return PessoaResponseDTO.valueOf(repository.findById(id));
    }

    @Override
    public List<PessoaResponseDTO> findByNome(String nome) {
       return repository.findByNome(nome).stream()
                .map(e -> PessoaResponseDTO.valueOf(e)).toList();
    }

    @Override
    public List<PessoaResponseDTO> findAll() {
        return repository.listAll().stream()
                .map(e -> PessoaResponseDTO.valueOf(e)).toList();
    }

    @Override
    public PessoaResponseDTO updateNome(String login, String nome) {
        Pessoa pessoa = repository.findByLogin(login);
        pessoa.setNome(nome);
        repository.persist(pessoa);
        return PessoaResponseDTO.valueOf(pessoa);
    }

    @Override
    public PessoaResponseDTO updateCPF(String login, String cpf) {
        Pessoa pessoa = repository.findByLogin(login);
        pessoa.setCpf(cpf);
        repository.persist(pessoa);
        return PessoaResponseDTO.valueOf(pessoa);
    }
}
