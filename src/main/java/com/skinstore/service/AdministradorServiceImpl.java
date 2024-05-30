package com.skinstore.service;

import java.util.ArrayList;
import java.util.List;

import com.skinstore.dto.AdministradorDTO;
import com.skinstore.dto.AdministradorResponseDTO;
import com.skinstore.dto.TelefoneDTO;
import com.skinstore.dto.UsuarioResponseDTO;
import com.skinstore.model.Administrador;
import com.skinstore.model.Pessoa;
import com.skinstore.model.Telefone;
import com.skinstore.model.Usuario;
import com.skinstore.repository.AdministradorRepository;
import com.skinstore.repository.PessoaRepository;
import com.skinstore.repository.UsuarioRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@ApplicationScoped
public class AdministradorServiceImpl implements AdministradorService {
    @Inject
    public AdministradorRepository administradorRepository;

    @Inject
    public PessoaRepository pessoaRepository;

    @Inject
    public UsuarioRepository usuarioRepository;

    @Inject
    public HashService hashService;

    @Override
    @Transactional
    public AdministradorResponseDTO create(@Valid AdministradorDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setLogin(dto.login());
        usuario.setSenha(hashService.getHashSenha(dto.senha()));

        usuarioRepository.persist(usuario);

        Pessoa pessoa = new Pessoa();
        pessoa.setNome(dto.nome());
        pessoa.setListaTelefone(new ArrayList<Telefone>());
        pessoa.setUsuario(usuario);

        for (TelefoneDTO tel : dto.telefones()) {
            Telefone t = new Telefone();
            t.setCodigoArea(tel.codigoArea());
            t.setNumero(tel.numero());
            pessoa.getListaTelefone().add(t);
        }

        pessoaRepository.persist(pessoa);

        Administrador administrador = new Administrador();
        administrador.setInscricao(dto.inscricao());
        administrador.setPessoa(pessoa);

        administradorRepository.persist(administrador);
        return AdministradorResponseDTO.valueOf(administrador);
    }

    @Override
    @Transactional
    public void update(Long id, AdministradorDTO dto) {
        Administrador admUpdate = administradorRepository.findById(id);

        admUpdate.getPessoa().setNome(dto.nome());;
        admUpdate.setInscricao(dto.inscricao());
        admUpdate.getPessoa().getUsuario().setLogin(dto.login());
        admUpdate.getPessoa().getUsuario().setSenha(dto.senha());
        admUpdate.getPessoa().getListaTelefone().clear();

        for(TelefoneDTO tel : dto.telefones()) {
            Telefone t = new Telefone();
            t.setCodigoArea(tel.codigoArea());
            t.setNumero(tel.numero());
            admUpdate.getPessoa().getListaTelefone().add(t);
        }
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

    public UsuarioResponseDTO login(String login, String senha) {
        Administrador adm = administradorRepository.findByLoginAndSenha(login, senha);
        return UsuarioResponseDTO.valueOf(adm.getPessoa());
    }

}