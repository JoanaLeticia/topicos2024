package com.skinstore.service;

import java.util.List;

import com.skinstore.dto.PessoaDTO;
import com.skinstore.dto.PessoaResponseDTO;
import jakarta.validation.Valid;

public interface PessoaService {
    public PessoaResponseDTO insert(@Valid PessoaDTO dto);
    public PessoaResponseDTO update(Long id, PessoaDTO dto);
    public void delete(Long id);
    public PessoaResponseDTO findById(Long id);
    public List<PessoaResponseDTO> findAll();
    public List<PessoaResponseDTO> findByNome(String nome);
    public PessoaResponseDTO updateNome(String login, String nome);
    public PessoaResponseDTO updateCPF(String login, String cpf);
}
