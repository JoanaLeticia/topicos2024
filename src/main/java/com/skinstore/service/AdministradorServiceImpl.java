package com.skinstore.service;

import java.util.List;

import com.skinstore.dto.AdministradorDTO;
import com.skinstore.dto.AdministradorResponseDTO;
import com.skinstore.model.Administrador;
import com.skinstore.repository.AdministradorRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@ApplicationScoped
public class AdministradorServiceImpl implements AdministradorService {
    @Inject
    public AdministradorRepository administradorRepository;

    @Override
    @Transactional
    public AdministradorResponseDTO create(@Valid AdministradorDTO dto) {
        Administrador adm = new Administrador();
        adm.setNome(dto.nome());
        adm.setInscricao(dto.inscricao());
        adm.setLogin(dto.login());
        adm.setSenha(dto.senha());

        administradorRepository.persist(adm);
        return AdministradorResponseDTO.valueOf(adm);
    }

    @Override
    @Transactional
    public void update(Long id, AdministradorDTO dto) {
        Administrador admUpdate = administradorRepository.findById(id);

        admUpdate.setNome(dto.nome());
        admUpdate.setLogin(dto.login());
        admUpdate.setSenha(dto.senha());
        admUpdate.setInscricao(dto.inscricao());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        administradorRepository.deleteById(id);
    }

    @Override
    public AdministradorResponseDTO findById(Long id) {
        Administrador adm = administradorRepository.findById(id);
        if (adm != null)
            return AdministradorResponseDTO.valueOf(adm);
        return null;
    }

    @Override
    public List<AdministradorResponseDTO> findAll() {
        return administradorRepository
                .listAll()
                .stream()
                .map(e -> AdministradorResponseDTO.valueOf(e)).toList();
    }

    @Override
    public List<AdministradorResponseDTO> findByNome(String nome) {
        return administradorRepository.findByNome(nome).stream()
                .map(e -> AdministradorResponseDTO.valueOf(e)).toList();
    }

}