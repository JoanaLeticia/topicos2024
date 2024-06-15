package com.skinstore.service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.skinstore.dto.ClienteDTO;
import com.skinstore.dto.ClienteResponseDTO;
import com.skinstore.model.Cidade;
import com.skinstore.model.Cliente;
import com.skinstore.model.Endereco;
import com.skinstore.model.Perfil;
import com.skinstore.model.Pessoa;
import com.skinstore.model.Telefone;
import com.skinstore.model.Usuario;
import com.skinstore.repository.CidadeRepository;
import com.skinstore.repository.ClienteRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class ClienteServiceImpl implements ClienteService {

    @Inject
    ClienteRepository repository;

    @Inject
    CidadeRepository cidadeRepository;

    @Override
    @Transactional
    public ClienteResponseDTO insert(ClienteDTO dto) throws Exception {
        if (repository.findByEmail(dto.login()) != null) {
            throw new Exception("Login já existe.");
        }

        Usuario usuario = new Usuario();
        usuario.setLogin(dto.login());
        usuario.setSenha(dto.senha());
        usuario.setPerfil(Perfil.CLIENTE);

        Pessoa pessoa = new Pessoa();
        pessoa.setCpf(dto.cpf());
        pessoa.setNome(dto.nome());

        Cliente cliente = new Cliente();
        cliente.setDataNascimento(dto.dataNascimento());

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
            cliente.getPessoa().getUsuario().setPerfil(Perfil.CLIENTE);
        } else {
            cliente.setEndereco(Collections.emptyList());
        } 

        repository.persist(cliente);

        return ClienteResponseDTO.valueOf(cliente);
    }

    @Override
    @Transactional
    public ClienteResponseDTO update(ClienteDTO dto, Long id) {

        Cliente clienteExistente = repository.findById(id);

        clienteExistente.setDataNascimento(dto.dataNascimento());
        clienteExistente.getPessoa().setCpf(dto.cpf());
        clienteExistente.getPessoa().setNome(dto.nome());
        clienteExistente.getPessoa().getUsuario().setLogin(dto.login());
        clienteExistente.getPessoa().getUsuario().setLogin(dto.senha());
        clienteExistente.getPessoa().getUsuario().setPerfil(Perfil.CLIENTE);

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

}
