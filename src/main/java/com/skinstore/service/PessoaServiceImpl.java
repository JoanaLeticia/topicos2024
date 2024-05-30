package com.skinstore.service;

import java.util.ArrayList;
import java.util.List;

import com.skinstore.dto.PessoaDTO;
import com.skinstore.dto.PessoaResponseDTO;
import com.skinstore.dto.TelefoneDTO;
import com.skinstore.model.Pessoa;
import com.skinstore.model.Telefone;
import com.skinstore.repository.PessoaRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@ApplicationScoped
public class PessoaServiceImpl implements PessoaService {
    @Inject
    public PessoaRepository pessoaRepository;

    @Override
    @Transactional
    public PessoaResponseDTO create(@Valid PessoaDTO dto) {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome(dto.nome());
        pessoa.setListaTelefone(new ArrayList<Telefone>());

        for (TelefoneDTO tel : dto.telefones()) {
            Telefone t = new Telefone();
            t.setCodigoArea(tel.codigoArea());
            t.setNumero(tel.numero());
            pessoa.getListaTelefone().add(t);
        }

        pessoaRepository.persist(pessoa);
        return PessoaResponseDTO.valueOf(pessoa);
    }

    @Override
    @Transactional
    public void update(Long id, PessoaDTO dto) {
        Pessoa pessoaUpdate =  pessoaRepository.findById(id);

        pessoaUpdate.setNome(dto.nome());
        pessoaUpdate.getListaTelefone().clear();

        for (TelefoneDTO tel : dto.telefones()) {
            Telefone t = new Telefone();
            t.setCodigoArea(tel.codigoArea());
            t.setNumero(tel.numero());
            pessoaUpdate.getListaTelefone().add(t);
        }

    }

    @Override
    @Transactional
    public void delete(Long id) {
        pessoaRepository.deleteById(id);
    }

    @Override
    public PessoaResponseDTO findById(Long id) {
        return PessoaResponseDTO.valueOf(pessoaRepository.findById(id));
    }

    @Override
    public List<PessoaResponseDTO> findAll() {
        return pessoaRepository
        .listAll()
        .stream()
        .map(e -> PessoaResponseDTO.valueOf(e)).toList();
    }

    @Override
    public List<PessoaResponseDTO> findByNome(String nome) {
        return pessoaRepository.findByNome(nome).stream()
        .map(e -> PessoaResponseDTO.valueOf(e)).toList();
    }
}
