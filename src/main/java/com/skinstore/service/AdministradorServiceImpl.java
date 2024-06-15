package com.skinstore.service;

import java.util.List;

import com.skinstore.dto.AdministradorDTO;
import com.skinstore.dto.AdministradorResponseDTO;
import com.skinstore.model.Administrador;
import com.skinstore.model.Perfil;
import com.skinstore.model.Pessoa;
import com.skinstore.model.Usuario;
import com.skinstore.repository.AdministradorRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class AdministradorServiceImpl implements AdministradorService {

    @Inject
    AdministradorRepository repository;

    @Override
    @Transactional
    public AdministradorResponseDTO insert(AdministradorDTO dto) {

        Usuario usuario = new Usuario();
        usuario.setLogin(dto.email());
        usuario.setSenha(dto.senha());
        usuario.setPerfil(Perfil.ADMIN);

        Pessoa pessoa = new Pessoa();
        pessoa.setCpf(dto.cpf());
        pessoa.setNome(dto.nome());

        Administrador administrador = new Administrador();
        administrador.setMatricula(dto.matricula());
        administrador.setPessoa(pessoa);

        repository.persist(administrador);

        return AdministradorResponseDTO.valueOf(administrador);
    }

    @Override
    @Transactional
    public AdministradorResponseDTO update(AdministradorDTO dto, Long id) {

        Administrador administradorAtt = repository.findById(id);

        administradorAtt.setMatricula(dto.matricula());
        administradorAtt.getPessoa().setCpf(dto.cpf());
        administradorAtt.getPessoa().setNome(dto.nome());
        administradorAtt.getPessoa().getUsuario().setLogin(dto.email());
        administradorAtt.getPessoa().getUsuario().setSenha(dto.senha());
        administradorAtt.getPessoa().getUsuario().setPerfil(Perfil.ADMIN);

        return AdministradorResponseDTO.valueOf(administradorAtt);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!repository.deleteById(id))
            throw new NotFoundException();
    }

    @Override
    public AdministradorResponseDTO findById(Long id) {
        Administrador administrador = repository.findById(id);
        if (administrador == null) {
            throw new EntityNotFoundException("Administrador não encontrado com ID: " + id);
        }
        return AdministradorResponseDTO.valueOf(administrador);
    }

    @Override
    public List<AdministradorResponseDTO> findByNome(String nome) {
        return repository.findByNome(nome).stream()
                .map(e -> AdministradorResponseDTO.valueOf(e)).toList();
    }

    @Override
    public List<AdministradorResponseDTO> findByAll() {
        return repository.listAll().stream()
                .map(e -> AdministradorResponseDTO.valueOf(e)).toList();
    }

}