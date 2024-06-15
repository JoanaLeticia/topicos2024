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
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class ProdutoServiceImpl implements ProdutoService {
    @Inject
    ProdutoRepository repository;

    @Override
    @Transactional
    public ProdutoResponseDTO insert(ProdutoDTO dto) {

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

        repository.persist(produto);

        return ProdutoResponseDTO.valueOf(produto);
    }

    @Override
    @Transactional
    public ProdutoResponseDTO update(ProdutoDTO dto, Long id) {
        Produto produtoUpdate = repository.findById(id);
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

        return ProdutoResponseDTO.valueOf(produtoUpdate);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!repository.deleteById(id))
            throw new NotFoundException();
    }

    @Override
    public ProdutoResponseDTO findById(Long id) {
        Produto produto = repository.findById(id);
        if (produto == null) {
            throw new EntityNotFoundException("Produto não encontrado com ID: " + id);
        }
        return ProdutoResponseDTO.valueOf(produto);
    }

    @Override
    public List<ProdutoResponseDTO> findByNome(String nome) {
        return repository.findByNome(nome).stream()
                .map(e -> ProdutoResponseDTO.valueOf(e)).toList();
    }

    @Override
    public List<ProdutoResponseDTO> findByAll() {
        return repository.listAll().stream()
                .map(e -> ProdutoResponseDTO.valueOf(e)).toList();
    }

    @Override
    public ProdutoResponseDTO updateNomeImagem(Long id, String nomeImagem) {
        Produto produto = repository.findById(id);
        produto.setNomeImagem(nomeImagem);
        return ProdutoResponseDTO.valueOf(produto);}
}
