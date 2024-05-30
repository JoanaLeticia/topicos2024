package com.skinstore.service;

import java.util.List;

import com.skinstore.dto.ProdutoDTO;
import com.skinstore.dto.ProdutoResponseDTO;
import com.skinstore.model.Arma;
import com.skinstore.model.Disponibilidade;
import com.skinstore.model.Exterior;
import com.skinstore.model.Produto;
import com.skinstore.model.Tipo;
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
        produto.setQuantEstoque(dto.quantEstoque());
        produto.setTipo(Tipo.valueOf(dto.idTipo()));
        produto.setArma(Arma.valueOf(dto.idArma()));
        produto.setExterior(Exterior.valueOf(dto.idExterior()));
        produto.setNumeroFloat(dto.numeroFloat());
        produto.setPattern(dto.pattern());
        produto.setDisponibilidade(Disponibilidade.valueOf(dto.idDisponibilidade()));

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
        produtoUpdate.setQuantEstoque(dto.quantEstoque());
        produtoUpdate.setTipo(Tipo.valueOf(dto.idTipo()));
        produtoUpdate.setArma(Arma.valueOf(dto.idArma()));
        produtoUpdate.setExterior(Exterior.valueOf(dto.idExterior()));
        produtoUpdate.setNumeroFloat(dto.numeroFloat());
        produtoUpdate.setPattern(dto.pattern());
        produtoUpdate.setDisponibilidade(Disponibilidade.valueOf(dto.idDisponibilidade()));
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
