package com.skinstore.service;

import java.util.List;

import com.skinstore.dto.CidadeDTO;
import com.skinstore.dto.CidadeResponseDTO;

import jakarta.validation.Valid;

public interface CidadeService {
    public CidadeResponseDTO create(@Valid CidadeDTO dto);
    public void update(Long id, CidadeDTO dto);
    public void delete(Long id);
    public CidadeResponseDTO findById(Long id);
    public List<CidadeResponseDTO> findAll();
    public List<CidadeResponseDTO> findByNome(String nome);
}
