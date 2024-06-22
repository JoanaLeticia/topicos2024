package com.skinstore.service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.jboss.logging.Logger;

import com.skinstore.dto.ClienteDTO;
import com.skinstore.dto.ClienteResponseDTO;
import com.skinstore.dto.UsuarioResponseDTO;
import com.skinstore.model.Cidade;
import com.skinstore.model.Cliente;
import com.skinstore.model.Endereco;
import com.skinstore.model.Pessoa;
import com.skinstore.model.Telefone;
import com.skinstore.model.Usuario;
import com.skinstore.repository.CidadeRepository;
import com.skinstore.repository.ClienteRepository;
import com.skinstore.repository.PessoaRepository;
import com.skinstore.repository.UsuarioRepository;
import com.skinstore.resource.ClienteResource;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class ClienteServiceImpl implements ClienteService {

    @Inject
    ClienteRepository repository;

    @Inject
    CidadeRepository cidadeRepository;

    @Inject
    UsuarioRepository usuarioRepository;

    @Inject
    PessoaRepository pessoaRepository;

    @Inject
    HashService hashService;

    private static final Logger LOG = Logger.getLogger(ClienteResource.class);

    @Override
    @Transactional
    public ClienteResponseDTO insert(@Valid ClienteDTO dto) {
        // Log de entrada no método
        LOG.info("Iniciando inserção do cliente");

        // Criação e persistência do usuário
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

        // Criação do cliente
        Cliente cliente = new Cliente();
        cliente.setDataNascimento(dto.dataNascimento());
        cliente.setPessoa(pessoa); // Atribui a pessoa ao cliente

        // Adiciona telefones ao cliente, se existirem
        if (dto.listaTelefone() != null && !dto.listaTelefone().isEmpty()) {
            List<Telefone> telefones = dto.listaTelefone().stream()
                    .map(tel -> {
                        Telefone telefone = new Telefone();
                        telefone.setCodigoArea(tel.codigoArea());
                        telefone.setNumero(tel.numero());
                        return telefone;
                    })
                    .collect(Collectors.toList());
            cliente.setTelefones(telefones);
        } else {
            cliente.setTelefones(Collections.emptyList());
        }

        // Adiciona endereços ao cliente, se existirem
        if (dto.listaEndereco() != null && !dto.listaEndereco().isEmpty()) {
            List<Endereco> enderecos = dto.listaEndereco().stream()
                    .map(end -> {
                        Endereco endereco = new Endereco();
                        endereco.setCep(end.cep());
                        endereco.setBairro(end.bairro());
                        endereco.setNumero(end.numero());
                        endereco.setLogradouro(end.logradouro());
                        endereco.setComplemento(end.complemento());

                        Cidade idCidade = cidadeRepository.findById(end.idCidade());
                        endereco.setCidade(idCidade);

                        return endereco;
                    })
                    .collect(Collectors.toList());
            cliente.setEndereco(enderecos);
        } else {
            cliente.setEndereco(Collections.emptyList());
        }

        // Persistência do cliente
        repository.persist(cliente);
        // Log após persistir administrador
        LOG.info("Cliente persistido com ID da pessoa: " + cliente.getPessoa().getId());

        return ClienteResponseDTO.valueOf(cliente);
    }

    @Override
    @Transactional
    public ClienteResponseDTO update(ClienteDTO dto, Long id) {

        Cliente clienteExistente = repository.findById(id);

        clienteExistente.setDataNascimento(dto.dataNascimento());
        clienteExistente.getPessoa().setCpf(dto.cpf());
        clienteExistente.getPessoa().setNome(dto.nome());
        clienteExistente.getPessoa().getUsuario().setLogin(dto.email());
        clienteExistente.getPessoa().getUsuario().setSenha(hashService.getHashSenha(dto.senha()));

        if (dto.listaTelefone() != null && !dto.listaTelefone().isEmpty()) {
            clienteExistente.getTelefones().clear();
            List<Telefone> telefones = dto.listaTelefone().stream()
                    .map(tel -> {
                        Telefone telefone = new Telefone();
                        telefone.setCodigoArea(tel.codigoArea());
                        telefone.setNumero(tel.numero());
                        return telefone;
                    })
                    .collect(Collectors.toList());
            clienteExistente.getTelefones().addAll(telefones);
        } else {
            clienteExistente.getTelefones().clear();
        }

        if (dto.listaEndereco() != null && !dto.listaEndereco().isEmpty()) {
            clienteExistente.getEndereco().clear();
            List<Endereco> enderecos = dto.listaEndereco().stream()
                    .map(end -> {
                        Endereco endereco = new Endereco();
                        endereco.setCep(end.cep());
                        endereco.setBairro(end.bairro());
                        endereco.setNumero(end.numero());
                        endereco.setLogradouro(end.logradouro());
                        endereco.setComplemento(end.complemento());
                        Cidade idCidade = cidadeRepository.findById(end.idCidade());
                        endereco.setCidade(idCidade);

                        return endereco;
                    })
                    .collect(Collectors.toList());
            clienteExistente.getEndereco().addAll(enderecos);
        } else {
            clienteExistente.getEndereco().clear();
        } 

        return ClienteResponseDTO.valueOf(clienteExistente);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!repository.deleteById(id))
            throw new NotFoundException();
    }

    @Override
    public ClienteResponseDTO findById(Long id) {
        Cliente cliente = repository.findById(id);
        if (cliente == null) {
            throw new EntityNotFoundException("Cliente não encontrado com ID: " + id);
        }
        return ClienteResponseDTO.valueOf(cliente);
    }

    @Override
    public List<ClienteResponseDTO> findByNome(String nome) {
        return repository.findByNome(nome).stream()
                .map(e -> ClienteResponseDTO.valueOf(e)).toList();
    }

    @Override
    public List<ClienteResponseDTO> findByAll() {
        return repository.listAll().stream()
                .map(e -> ClienteResponseDTO.valueOf(e)).toList();
    }

    public UsuarioResponseDTO login(String login, String senha) {
        // Log de entrada no método
        LOG.info("Tentativa de login com login: " + login);
        
        // Busca pelo cliente
        Cliente cliente = repository.findByLoginAndSenha(login, senha);
    
        // Verifica se o cliente é nulo
        if (cliente == null) {
            // Log de erro
            LOG.info("Cliente não encontrado para o login: " + login + " e senha fornecida");
            throw new RuntimeException("Cliente não encontrado");
        }
    
        // Verifica se a pessoa associada ao cliente é nula
        if (cliente.getPessoa() == null) {
            // Log de erro
            LOG.info("Pessoa não encontrada para o cliente com login: " + login);
            throw new RuntimeException("Pessoa não encontrada para o cliente");
        }
    
        // Log de sucesso
        LOG.info("Login bem-sucedido para o cliente com login: " + login);
    
        return UsuarioResponseDTO.valueOf(cliente.getPessoa().getUsuario());
    }

    @Override
    public ClienteResponseDTO findByLogin(String login) {
        Cliente cliente = repository.findByLogin(login);
        if (cliente == null) {
            throw new EntityNotFoundException("Cliente não encontrado com login: " + login);
        }
        return ClienteResponseDTO.valueOf(cliente);
    }

}
