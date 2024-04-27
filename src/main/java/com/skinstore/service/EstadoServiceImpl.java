package com.skinstore.service;

import java.util.List;

import com.skinstore.dto.EstadoDTO;
import com.skinstore.dto.EstadoResponseDTO;
import com.skinstore.model.Estado;
import com.skinstore.repository.EstadoRepository;
import com.skinstore.validation.ValidationException;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@ApplicationScoped
public class EstadoServiceImpl implements EstadoService {
    @Inject
    public EstadoRepository estadoRepository;

    @Override
    @Transactional
    public EstadoResponseDTO create(@Valid EstadoDTO dto) {
        validarNomeEstado(dto.nome());

        Estado estado = new Estado();
        estado.setNome(dto.nome());
        estado.setSigla(dto.sigla());

        estadoRepository.persist(estado);
        return EstadoResponseDTO.valueOf(estado);
    }

    public void validarNomeEstado(String nome) {
        Estado estado = estadoRepository.findByNomeCompleto(nome);
        if(estado != null)
            throw new ValidationException("nome", "Esse nome '"+nome+"' já existe.");
    }

    @Override
    @Transactional
    public void update(Long id, EstadoDTO dto) {
        Estado estadoUpdate = estadoRepository.findById(id);

        estadoUpdate.setNome(dto.nome());
        estadoUpdate.setSigla(dto.sigla());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        estadoRepository.deleteById(id);
    }

    @Override
    public EstadoResponseDTO findById(Long id) {
        Estado estado = estadoRepository.findById(id);
        if (estado != null)
            return EstadoResponseDTO.valueOf(estado);
        return null;
    }

    @Override
    public List<EstadoResponseDTO> findAll() {
        return estadoRepository
                .listAll()
                .stream()
                .map(e -> EstadoResponseDTO.valueOf(e)).toList();
    }

    @Override
    public List<EstadoResponseDTO> findByNome(String nome) {
        return estadoRepository.findByNome(nome).stream()
                .map(e -> EstadoResponseDTO.valueOf(e)).toList();
    }

    @Override
    public List<EstadoResponseDTO> findBySigla(String sigla) {
        return estadoRepository.findBySigla(sigla).stream()
                .map(e -> EstadoResponseDTO.valueOf(e)).toList();
    }
}
