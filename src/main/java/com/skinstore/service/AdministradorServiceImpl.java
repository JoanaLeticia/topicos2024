package com.skinstore.service;

import java.util.List;

import org.jboss.logging.Logger;

import com.skinstore.dto.AdministradorDTO;
import com.skinstore.dto.AdministradorResponseDTO;
import com.skinstore.dto.UsuarioResponseDTO;
import com.skinstore.model.Administrador;
import com.skinstore.model.Pessoa;
import com.skinstore.model.Usuario;
import com.skinstore.repository.AdministradorRepository;
import com.skinstore.repository.PessoaRepository;
import com.skinstore.repository.UsuarioRepository;
import com.skinstore.resource.AdministradorResource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@ApplicationScoped
public class AdministradorServiceImpl implements AdministradorService {

    @Inject
    AdministradorRepository repository;

    @Inject
    PessoaRepository pessoaRepository;

    @Inject
    UsuarioRepository usuarioRepository;
    
    @Inject
    HashService hashService;

    private static final Logger LOG = Logger.getLogger(AdministradorResource.class);

    @Override
    @Transactional
    public AdministradorResponseDTO insert(@Valid AdministradorDTO dto) {
        // Log de entrada no método
        LOG.info("Iniciando inserção do administrador");
    
        Usuario usuario = new Usuario();
        usuario.setLogin(dto.email());
        usuario.setSenha(hashService.getHashSenha(dto.senha()));
        
        usuarioRepository.persist(usuario);
        // Log após persistir usuário
        LOG.info("Usuário persistido com ID: " + usuario.getId());
    
        Pessoa pessoa = new Pessoa();
        pessoa.setCpf(dto.cpf());
        pessoa.setNome(dto.nome());
        pessoa.setUsuario(usuario);
    
        pessoaRepository.persist(pessoa);
        // Log após persistir pessoa
        LOG.info("Pessoa persistida com ID: " + pessoa.getId());
    
        Administrador administrador = new Administrador();
        administrador.setMatricula(dto.matricula());
        administrador.setPessoa(pessoa);
    
        repository.persist(administrador);
        // Log após persistir administrador
        LOG.info("Administrador persistido com ID da pessoa: " + administrador.getPessoa().getId());
    
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
        administradorAtt.getPessoa().getUsuario().setSenha(hashService.getHashSenha(dto.senha()));

        return AdministradorResponseDTO.valueOf(administradorAtt);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public AdministradorResponseDTO findById(Long id) {
        Administrador administrador = repository.findById(id);
        if (administrador == null) {
            throw new EntityNotFoundException("Administrador não encontrado com ID: " + id);
        }
        return AdministradorResponseDTO.valueOf(repository.findById(id));
    }

    @Override
    public List<AdministradorResponseDTO> findByNome(String nome) {
        return repository.findByNome(nome).stream()
                .map(e -> AdministradorResponseDTO.valueOf(e)).toList();
    }

    @Override
    public List<AdministradorResponseDTO> findAll() {
        return repository
                .listAll()
                .stream()
                .map(e -> AdministradorResponseDTO.valueOf(e))
                .toList();
    }

    public UsuarioResponseDTO login(String login, String senha) {
        // Log de entrada no método
        LOG.info("Tentativa de login com login: " + login);
        
        // Busca pelo administrador
        Administrador adm = repository.findByLoginAndSenha(login, senha);
    
        // Verifica se o administrador é nulo
        if (adm == null) {
            // Log de erro
            LOG.info("Administrador não encontrado para o login: " + login + " e senha fornecida");
            throw new RuntimeException("Administrador não encontrado");
        }
    
        // Verifica se a pessoa associada ao administrador é nula
        if (adm.getPessoa() == null) {
            // Log de erro
            LOG.info("Pessoa não encontrada para o administrador com login: " + login);
            throw new RuntimeException("Pessoa não encontrada para o administrador");
        }
    
        // Log de sucesso
        LOG.info("Login bem-sucedido para o administrador com login: " + login);
    
        return UsuarioResponseDTO.valueOf(adm.getPessoa().getUsuario());
    }
    
}