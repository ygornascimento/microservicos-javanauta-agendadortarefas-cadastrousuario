package br.tec.itlabs.agendadorcadastrousuario.business.converter;

import br.tec.itlabs.agendadorcadastrousuario.business.dto.EnderecoDTO;
import br.tec.itlabs.agendadorcadastrousuario.business.dto.TelefoneDTO;
import br.tec.itlabs.agendadorcadastrousuario.business.dto.UsuarioDTO;
import br.tec.itlabs.agendadorcadastrousuario.infrastructure.entity.Endereco;
import br.tec.itlabs.agendadorcadastrousuario.infrastructure.entity.Telefone;
import br.tec.itlabs.agendadorcadastrousuario.infrastructure.entity.Usuario;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UsuarioConverter {

    public Usuario converteParaUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuario = new Usuario();
        usuario.setNome(usuarioDTO.getNome());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setSenha(usuarioDTO.getSenha());
        usuario.setEnderecos(converteListaEnderecoDTOparaListaEndereco(usuarioDTO.getEnderecos()));
        usuario.setTelefones(converteListaTelefoneDTOparaListaTelefone(usuarioDTO.getTelefones()));

        return usuario;
    }

    private List<Endereco> converteListaEnderecoDTOparaListaEndereco(List<EnderecoDTO> enderecosDTO) {
        List<Endereco> enderecos = new ArrayList<>();
        for (EnderecoDTO enderecoDTO: enderecosDTO) {
            enderecos.add(converteEnderecoDTOparaEndereco(enderecoDTO));
        }
        return  enderecos;
    }

    private Endereco converteEnderecoDTOparaEndereco(EnderecoDTO enderecoDTO) {
        return Endereco.builder()
                .rua(enderecoDTO.getRua())
                .numero(enderecoDTO.getNumero())
                .cidade(enderecoDTO.getCidade())
                .complemento(enderecoDTO.getComplemento())
                .cep(enderecoDTO.getCep())
                .estado(enderecoDTO.getEstado())
                .build();
    }

    private List<Telefone> converteListaTelefoneDTOparaListaTelefone(List<TelefoneDTO> telefonesDTO) {
        return telefonesDTO.stream().map(this::converteTelefoneDTOparaTelefone).toList();
    }

    private Telefone converteTelefoneDTOparaTelefone(TelefoneDTO telefoneDTO) {
        return Telefone.builder()
                .ddd(telefoneDTO.getDdd())
                .numero(telefoneDTO.getNumero())
                .build();
    }

    public UsuarioDTO converteParaUsuarioDTO(Usuario usuario) {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setNome(usuario.getNome());
        usuarioDTO.setEmail(usuario.getEmail());
        usuarioDTO.setSenha(usuario.getSenha());
        usuarioDTO.setEnderecos(converteListaEnderecoParaListaEnderecoDTO(usuario.getEnderecos()));
        usuarioDTO.setTelefones(converteListaTelefoneparaListaTelefoneDTO(usuario.getTelefones()));

        return usuarioDTO;
    }

    private List<EnderecoDTO> converteListaEnderecoParaListaEnderecoDTO(List<Endereco> enderecos) {
        List<EnderecoDTO> enderecosDTO = new ArrayList<>();
        for (Endereco endereco: enderecos) {
            enderecosDTO.add(converteParaEndereco(endereco));
        }
        return  enderecosDTO;
    }

    private EnderecoDTO converteParaEndereco(Endereco endereco) {
        return EnderecoDTO.builder()
                .rua(endereco.getRua())
                .numero(endereco.getNumero())
                .cidade(endereco.getCidade())
                .complemento(endereco.getComplemento())
                .cep(endereco.getCep())
                .estado(endereco.getEstado())
                .build();
    }

    private List<TelefoneDTO> converteListaTelefoneparaListaTelefoneDTO(List<Telefone> telefones) {
        return telefones.stream().map(this::converteTelefoneparaTelefoneDTO).toList();
    }

    private TelefoneDTO converteTelefoneparaTelefoneDTO(Telefone telefone) {
        return TelefoneDTO.builder()
                .ddd(telefone.getDdd())
                .numero(telefone.getNumero())
                .build();
    }

    public Usuario updateUsuario(UsuarioDTO usuarioDTO, Usuario usuarioEntity) {
        return Usuario.builder()
                .nome(usuarioDTO.getNome() != null ? usuarioDTO.getNome() : usuarioEntity.getNome())
                .id(usuarioEntity.getId())
                .senha(usuarioDTO.getSenha() != null ? usuarioDTO.getSenha() : usuarioEntity.getSenha())
                .email(usuarioDTO.getEmail() != null ? usuarioDTO.getEmail() : usuarioEntity.getEmail())
                .enderecos(usuarioEntity.getEnderecos())
                .telefones(usuarioEntity.getTelefones())
                .build();
    }
}
