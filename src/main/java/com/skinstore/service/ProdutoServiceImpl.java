package com.skinstore.service;

import java.util.List;

import com.skinstore.dto.ProdutoDTO;
import com.skinstore.dto.ProdutoResponseDTO;
import com.skinstore.model.Produto;
import com.skinstore.repository.ProdutoRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@ApplicationScoped
public class ProdutoServiceImpl implements ProdutoService {
    @Inject
    public ProdutoRepository produtoRepository;

    @Override
    @Transactional
    public ProdutoResponseDTO create(@Valid ProdutoDTO dto) {
        Produto produto = new Produto();
        produto.setNome(dto.nome());
        produto.setLinkSteam(dto.linkSteam());
        produto.setValor(dto.valor());
        produto.setTipo(dto.tipo());
        produto.setArma(dto.arma());
        produto.setExterior(dto.exterior());
        produto.setNumeroFloat(dto.numeroFloat());
        produto.setPattern(dto.pattern());
        produto.setDisponibilidade(dto.disponibilidade());

        produtoRepository.persist(produto);
        return ProdutoResponseDTO.valueOf(produto);
    }

    @Override
    @Transactional
    public void update(Long id, ProdutoDTO dto) {
        Produto produtoUpdate = produtoRepository.findById(id);

        produtoUpdate.setNome(dto.nome());
        produtoUpdate.setLinkSteam(dto.linkSteam());
        produtoUpdate.setValor(dto.valor());
        produtoUpdate.setTipo(dto.tipo());
        produtoUpdate.setArma(dto.arma());
        produtoUpdate.setExterior(dto.exterior());
        produtoUpdate.setNumeroFloat(dto.numeroFloat());
        produtoUpdate.setPattern(dto.pattern());
        produtoUpdate.setDisponibilidade(dto.disponibilidade());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        produtoRepository.deleteById(id);
    }

    @Override
    public ProdutoResponseDTO findById(Long id) {
        Produto produto = produtoRepository.findById(id);
        if (produto != null)
            return ProdutoResponseDTO.valueOf(produto);
        return null;
    }

    @Override
    public List<ProdutoResponseDTO> findAll() {
        return produtoRepository
                .listAll()
                .stream()
                .map(e -> ProdutoResponseDTO.valueOf(e)).toList();
    }

    @Override
    public List<ProdutoResponseDTO> findByNome(String nome) {
        return produtoRepository.findByNome(nome).stream()
                .map(e -> ProdutoResponseDTO.valueOf(e)).toList();
    }
}
