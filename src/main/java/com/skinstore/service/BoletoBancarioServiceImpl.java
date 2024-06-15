package com.skinstore.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.skinstore.dto.BoletoBancarioDTO;
import com.skinstore.dto.BoletoBancarioResponseDTO;
import com.skinstore.model.BoletoBancario;
import com.skinstore.repository.BoletoBancarioRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class BoletoBancarioServiceImpl implements BoletoBancarioService {

    @Inject
    BoletoBancarioRepository repository;

    @Override
    @Transactional
    public BoletoBancarioResponseDTO insert(BoletoBancarioDTO dto) {
        BoletoBancario novoBoletoBancario = new BoletoBancario();
        novoBoletoBancario.setBanco(dto.banco());
        novoBoletoBancario.setNumeroBoleto(dto.numeroBoleto());
        if (dto.dataVencimento().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("A data de vencimento não pode ser anterior ao dia atual");
        }
        novoBoletoBancario.setDataVencimento(dto.dataVencimento());

        repository.persist(novoBoletoBancario);

        return BoletoBancarioResponseDTO.valueOf(novoBoletoBancario);
    }

    @Override
    @Transactional
    public BoletoBancarioResponseDTO update(BoletoBancarioDTO dto, Long id) {

        BoletoBancario boletoExistente = repository.findById(id);
        boletoExistente.setBanco(dto.banco());
        boletoExistente.setNumeroBoleto(dto.numeroBoleto());
        boletoExistente.setDataVencimento(dto.dataVencimento());

        return BoletoBancarioResponseDTO.valueOf(boletoExistente);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!repository.deleteById(id))
            throw new NotFoundException();
    }

    @Override
    public BoletoBancarioResponseDTO findById(Long id) {
        BoletoBancario boleto = repository.findById(id);
        if (boleto == null) {
            throw new EntityNotFoundException("Boleto Bancario não encontrado com ID: " + id);
        }
        return BoletoBancarioResponseDTO.valueOf(boleto);
    }

    @Override
    public List<BoletoBancarioResponseDTO> findByAll() {
        return repository.listAll().stream()
                .map(e -> BoletoBancarioResponseDTO.valueOf(e)).toList();
    }

    @Override
    public List<BoletoBancarioResponseDTO> findByNumeroBoleto(String numero) {
        List<BoletoBancario> list = repository.findByNumeroBoleto(numero);
        return list.stream().map(u -> BoletoBancarioResponseDTO.valueOf(u)).collect(Collectors.toList());
    }

}

