package com.skinstore.dto;

import java.util.Date;
import java.util.List;

import com.skinstore.model.Administrador;
import com.skinstore.model.Cliente;
import com.skinstore.model.Usuario;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record UsuarioResponseDTO(
    String login,
    int perfil,
    String nome,
    String cpf,
    Integer matricula,
    Date dataNascimento,
    List<TelefoneResponseDTO> listaTelefone,
    List<EnderecoResponseDTO> listaEndereco
) {

    // Construtor primário que inicializa todos os campos
    public UsuarioResponseDTO(
        String login,
        int perfil,
        String nome,
        String cpf,
        Integer matricula,
        Date dataNascimento,
        List<TelefoneResponseDTO> listaTelefone,
        List<EnderecoResponseDTO> listaEndereco
    ) {
        this.login = login;
        this.perfil = perfil;
        this.nome = nome;
        this.cpf = cpf;
        this.matricula = matricula;
        this.dataNascimento = dataNascimento;
        this.listaTelefone = listaTelefone;
        this.listaEndereco = listaEndereco;
    }

    public static UsuarioResponseDTO valueOf(Usuario usuario) {
        if (usuario == null) {
            return null;
        }
        return new UsuarioResponseDTO(
            usuario.getLogin(),
            usuario.getPerfil(),
            null,
            null,
            null,
            null,
            null,
            null
        );
    }

    public static UsuarioResponseDTO fromCliente(Cliente cliente) {
        if (cliente == null) {
            return null;
        }
        List<TelefoneResponseDTO> listaTelefones = cliente.getTelefones()
            .stream()
            .map(TelefoneResponseDTO::valueOf)
            .toList();

        List<EnderecoResponseDTO> listaEndereco = cliente.getEndereco()
            .stream()
            .map(EnderecoResponseDTO::valueOf)
            .toList();

        return new UsuarioResponseDTO(
            cliente.getPessoa().getUsuario().getLogin(),
            cliente.getPessoa().getUsuario().getPerfil(),
            cliente.getPessoa().getNome(),
            cliente.getPessoa().getCpf(),
            null,
            cliente.getDataNascimento(),
            listaTelefones,
            listaEndereco
        );
    }

    public static UsuarioResponseDTO fromAdministrador(Administrador administrador) {
        if (administrador == null) {
            return null;
        }
        return new UsuarioResponseDTO(
            administrador.getPessoa().getUsuario().getLogin(),
            administrador.getPessoa().getUsuario().getPerfil(),
            administrador.getPessoa().getNome(),
            administrador.getPessoa().getCpf(),
            administrador.getMatricula(),
            null,
            null,
            null
        );
    }
}
