package com.skinstore.service;

import java.util.List;

import com.skinstore.dto.EstadoDTO;
import com.skinstore.dto.EstadoResponseDTO;

public interface EstadoService {
    public EstadoResponseDTO insert(EstadoDTO dto);
    public EstadoResponseDTO update(EstadoDTO dto, Long id);
    public void delete(Long id);
    public EstadoResponseDTO findById(Long id);
    public List<EstadoResponseDTO> findByNome(String nome);
    public List<EstadoResponseDTO> findByAll();
    public List<EstadoResponseDTO> findBySigla(String sigla);
}
