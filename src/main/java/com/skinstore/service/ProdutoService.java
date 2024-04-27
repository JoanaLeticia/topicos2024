package com.skinstore.service;

import java.util.List;

import com.skinstore.dto.ProdutoDTO;
import com.skinstore.dto.ProdutoResponseDTO;

import jakarta.validation.Valid;

public interface ProdutoService {
    public ProdutoResponseDTO create(@Valid ProdutoDTO dto);
    public void update(Long id, ProdutoDTO dto);
    public void delete(Long id);
    public ProdutoResponseDTO findById(Long id);
    public List<ProdutoResponseDTO> findAll();
    public List<ProdutoResponseDTO> findByNome(String nome);
}
