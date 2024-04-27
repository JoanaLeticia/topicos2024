package com.skinstore.service;

import java.util.List;

import com.skinstore.dto.CidadeDTO;
import com.skinstore.dto.CidadeResponseDTO;
import com.skinstore.model.Cidade;
import com.skinstore.repository.CidadeRepository;
import com.skinstore.repository.EstadoRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@ApplicationScoped
public class CidadeServiceImpl implements CidadeService {
    @Inject
    public CidadeRepository cidadeRepository;

    @Inject
    EstadoRepository estadoRepository;

    @Override
    @Transactional
    public CidadeResponseDTO create(@Valid CidadeDTO dto) {
        Cidade novaCidade = new Cidade();
        novaCidade.setNome(dto.nome());
        novaCidade.setEstado(estadoRepository.findById(dto.idEstado()));

        cidadeRepository.persist(novaCidade);
        return CidadeResponseDTO.valueOf(novaCidade);
    }

    @Override
    @Transactional
    public void update(Long id, CidadeDTO dto) {
        Cidade cidadeUpdate = cidadeRepository.findById(id);

        if(cidadeUpdate == null) {
            throw new EntityNotFoundException("Cidade com ID " + id + " não encontrada");
        }

        cidadeUpdate.setNome(dto.nome());
        cidadeUpdate.setEstado(estadoRepository.findById(dto.idEstado()));

    }

    @Override
    @Transactional
    public void delete(Long id) {
        cidadeRepository.deleteById(id);
    }

    @Override
    public CidadeResponseDTO findById(Long id) {
        Cidade cidade = cidadeRepository.findById(id);
        if (cidade != null)
            return CidadeResponseDTO.valueOf(cidade);
        return null;
    }

    @Override
    public List<CidadeResponseDTO> findAll() {
        return cidadeRepository
                .listAll()
                .stream()
                .map(e -> CidadeResponseDTO.valueOf(e)).toList();
    }

    @Override
    public List<CidadeResponseDTO> findByNome(String nome) {
        return cidadeRepository.findByNome(nome).stream()
                .map(e -> CidadeResponseDTO.valueOf(e)).toList();
    }
}
