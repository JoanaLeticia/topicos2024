package com.skinstore.service;

import java.util.ArrayList;
import java.util.List;

import com.skinstore.dto.ClienteDTO;
import com.skinstore.dto.ClienteResponseDTO;
import com.skinstore.dto.TelefoneDTO;
import com.skinstore.model.Cliente;
import com.skinstore.model.Pessoa;
import com.skinstore.model.Telefone;
import com.skinstore.model.Usuario;
import com.skinstore.repository.ClienteRepository;
import com.skinstore.repository.PessoaRepository;
import com.skinstore.repository.UsuarioRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@ApplicationScoped
public class ClienteServiceImpl implements ClienteService {
    @Inject
    public ClienteRepository clienteRepository;

    @Inject
    public PessoaRepository pessoaRepository;

    @Inject
    public UsuarioRepository usuarioRepository;

    @Inject
    public HashService hashService;

    @Override
    @Transactional
    public ClienteResponseDTO create(@Valid ClienteDTO dto) {
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

        Cliente cliente = new Cliente();
        cliente.setCpf(dto.cpf());
        cliente.setPessoa(pessoa);

        clienteRepository.persist(cliente);
        return ClienteResponseDTO.valueOf(cliente);
    }

    @Override
    @Transactional
    public void update(Long id, ClienteDTO dto) {
        Cliente clienteUpdate = clienteRepository.findById(id);

        clienteUpdate.getPessoa().setNome(dto.nome());;
        clienteUpdate.setCpf(dto.cpf());
        clienteUpdate.getPessoa().getUsuario().setLogin(dto.login());
        clienteUpdate.getPessoa().getUsuario().setSenha(dto.senha());
        clienteUpdate.getPessoa().getListaTelefone().clear();

        for(TelefoneDTO tel : dto.telefones()) {
            Telefone t = new Telefone();
            t.setCodigoArea(tel.codigoArea());
            t.setNumero(tel.numero());
            clienteUpdate.getPessoa().getListaTelefone().add(t);
        }
    }

    @Override
    @Transactional
    public void delete(Long id) {
        clienteRepository.deleteById(id);
    }

    @Override
    public ClienteResponseDTO findById(Long id) {
        Cliente cliente = clienteRepository.findById(id);
        if (cliente != null)
            return ClienteResponseDTO.valueOf(cliente);
        return null;
    }

    @Override
    public List<ClienteResponseDTO> findAll() {
        return clienteRepository
            .listAll()
            .stream()
            .map(e -> ClienteResponseDTO.valueOf(e)).toList();
    }

    @Override
    public List<ClienteResponseDTO> findByNome(String nome) {
        return clienteRepository.findByNome(nome).stream()
            .map(e -> ClienteResponseDTO.valueOf(e)).toList();
    }
}
