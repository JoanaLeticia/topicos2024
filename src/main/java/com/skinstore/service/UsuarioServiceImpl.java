package com.skinstore.service;

import java.util.Date;
import java.util.List;
import com.skinstore.dto.UsuarioDTO;
import com.skinstore.dto.UsuarioResponseDTO;
import com.skinstore.model.Administrador;
import com.skinstore.model.Cliente;
import com.skinstore.model.Pessoa;
import com.skinstore.model.Usuario;
import com.skinstore.repository.AdministradorRepository;
import com.skinstore.repository.ClienteRepository;
import com.skinstore.repository.EnderecoRepository;
import com.skinstore.repository.PedidoRepository;
import com.skinstore.repository.PessoaRepository;
import com.skinstore.repository.TelefoneRepository;
import com.skinstore.repository.UsuarioRepository;
import com.skinstore.validation.ValidationException;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@ApplicationScoped
public class UsuarioServiceImpl implements UsuarioService {

    @Inject
    UsuarioRepository repository;

    @Inject
    PedidoRepository pedidoRepository;

    @Inject
    PessoaRepository pessoaRepository;

    @Inject
    AdministradorRepository administradorRepository;

    @Inject
    ClienteRepository clienteRepository;

    @Inject
    TelefoneRepository telefoneRepository;

    @Inject
    EnderecoRepository enderecoRepository;

    @Override
    @Transactional
    public UsuarioResponseDTO insert(@Valid UsuarioDTO dto) {

        if (repository.findByLogin(dto.login()) != null) {
            throw new ValidationException("login", "Login já existe.");
        }

        Usuario novoUsuario = new Usuario();
        novoUsuario.setLogin(dto.login());
        novoUsuario.setSenha(dto.senha());
        novoUsuario.setPerfil(dto.idPerfil());

        repository.persist(novoUsuario);

        return UsuarioResponseDTO.valueOf(novoUsuario);
    }

    @Override
    @Transactional
    public UsuarioResponseDTO update(UsuarioDTO dto, Long id) {

        Usuario attUsuario = new Usuario();
        attUsuario.setLogin(dto.login());
        attUsuario.setSenha(dto.senha());
        attUsuario.setPerfil(dto.idPerfil());

        repository.persist(attUsuario);

        return UsuarioResponseDTO.valueOf(attUsuario);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public UsuarioResponseDTO findById(Long id) {
        return UsuarioResponseDTO.valueOf(repository.findById(id));
    }
    
    @Override
    public List<UsuarioResponseDTO> findByNome(String nome) {
       return repository.findByNome(nome).stream()
                .map(e -> UsuarioResponseDTO.valueOf(e)).toList();
    }

    @Override
    public List<UsuarioResponseDTO> findByAll() {
        return repository.listAll().stream()
                .map(e -> UsuarioResponseDTO.valueOf(e)).toList();
    }

    @Override
    public UsuarioResponseDTO findByLoginAndSenha(String login, String senha) {
        Usuario usuario = repository.findByLoginAndSenha(login, senha);
        if (usuario == null)
            throw new ValidationException("login", "Login ou senha inválido");

        return UsuarioResponseDTO.valueOf(usuario);
    }

    @Override
    public UsuarioResponseDTO findByLogin(String login) {
        Usuario usuario = repository.findByLogin(login);
        if (usuario == null)
            throw new ValidationException("login", "Login inválido");

        if (usuario.getPerfil() == 1) {
            Administrador adm = administradorRepository.findByLogin(login);
            return UsuarioResponseDTO.fromAdministrador(adm);
        } else if (usuario.getPerfil() == 2) {
            Cliente cliente = clienteRepository.findByLogin(login);
            return UsuarioResponseDTO.fromCliente(cliente);
        } else {
            throw new ValidationException("perfil", "Perfil de usuário não reconhecido");
        }
    }

    @Override
    @Transactional
    public UsuarioResponseDTO updateSenha(String login, String senha) {
        Usuario usuario = repository.findByLogin(login);
        usuario.setSenha(senha);
        repository.persist(usuario);
        return UsuarioResponseDTO.valueOf(usuario);
    }

    @Override
    @Transactional
    public UsuarioResponseDTO updateNome(String login, String nome) {
        Usuario usuario = repository.findByLogin(login);
        Pessoa pessoa = pessoaRepository.findByLogin(login);
        pessoa.setNome(nome);
        pessoaRepository.persist(pessoa);

        return UsuarioResponseDTO.valueOf(usuario);
    }

    @Override
    @Transactional
    public UsuarioResponseDTO updateMatricula(String login, Integer novaMatricula) {
        Administrador adm = administradorRepository.findByLogin(login);
        if(adm == null)
            throw new IllegalArgumentException("Administrador não encontrado para o login: " + login);
        
        adm.setMatricula(novaMatricula);
        administradorRepository.persist(adm);

        return UsuarioResponseDTO.valueOf(adm.getPessoa().getUsuario());
    }

    @Override
    @Transactional
    public UsuarioResponseDTO updateDataNasc(String login, Date novaDataNascimento) {
        Cliente cliente = clienteRepository.findByLogin(login);
        if (cliente == null) {
            throw new IllegalArgumentException("Cliente não encontrado para o login: " + login);
        }

        cliente.setDataNascimento(novaDataNascimento);
        clienteRepository.persist(cliente);

        return UsuarioResponseDTO.valueOf(cliente.getPessoa().getUsuario());
    }

}
