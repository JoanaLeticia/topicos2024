package com.skinstore.service;

import java.util.List;

import com.skinstore.dto.EnderecoDTO;
import com.skinstore.dto.EnderecoResponseDTO;

public interface EnderecoService {
    public EnderecoResponseDTO insert(EnderecoDTO dto);
    public EnderecoResponseDTO update(EnderecoDTO dto, Long id);
    public void delete(Long id);
    public EnderecoResponseDTO findById(Long id);
    public List<EnderecoResponseDTO> findByCep(String nome);
    public List<EnderecoResponseDTO> findByAll(); 
}
