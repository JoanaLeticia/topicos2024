package com.skinstore.service;

import java.util.ArrayList;
import java.util.List;

import com.skinstore.dto.ClienteDTO;
import com.skinstore.dto.ClienteResponseDTO;
import com.skinstore.dto.TelefoneDTO;
import com.skinstore.model.Cliente;
import com.skinstore.model.Telefone;
import com.skinstore.repository.ClienteRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@ApplicationScoped
public class ClienteServiceImpl implements ClienteService {
    @Inject
    public ClienteRepository clienteRepository;

    @Override
    @Transactional
    public ClienteResponseDTO create(@Valid ClienteDTO dto) {
        Cliente cliente = new Cliente();
        cliente.setNome(dto.nome());
        cliente.setCpf(dto.cpf());
        cliente.setLogin(dto.login());
        cliente.setSenha(dto.senha());
        cliente.setListaTelefone(new ArrayList<Telefone>());

        for (TelefoneDTO tel : dto.telefones()) {
            Telefone t = new Telefone();
            t.setCodigoArea(tel.codigoArea());
            t.setNumero(tel.numero());
            cliente.getListaTelefone().add(t);
        }

        clienteRepository.persist(cliente);
        return ClienteResponseDTO.valueOf(cliente);
    }

    @Override
    @Transactional
    public void update(Long id, ClienteDTO dto) {
        Cliente clienteUpdate = clienteRepository.findById(id);

        clienteUpdate.setNome(dto.nome());
        clienteUpdate.setCpf(dto.cpf());
        clienteUpdate.setLogin(dto.login());
        clienteUpdate.setSenha(dto.senha());
        clienteUpdate.getListaTelefone().clear();

        for(TelefoneDTO tel : dto.telefones()) {
            Telefone t = new Telefone();
            t.setCodigoArea(tel.codigoArea());
            t.setNumero(tel.numero());
            clienteUpdate.getListaTelefone().add(t);
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
