package com.skinstore.service;

import java.util.List;

import com.skinstore.dto.EstadoDTO;
import com.skinstore.dto.EstadoResponseDTO;

import jakarta.validation.Valid;

public interface EstadoService {
    public EstadoResponseDTO create(@Valid EstadoDTO dto);
    public void update(Long id, EstadoDTO dto);
    public void delete(Long id);
    public EstadoResponseDTO findById(Long id);
    public List<EstadoResponseDTO> findAll();
    public List<EstadoResponseDTO> findByNome(String nome);
    public List<EstadoResponseDTO> findBySigla(String sigla);
}
