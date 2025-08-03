package br.tec.itlabs.agendadorcadastrousuario.business.service;

import br.tec.itlabs.agendadorcadastrousuario.business.converter.UsuarioConverter;
import br.tec.itlabs.agendadorcadastrousuario.business.dto.UsuarioDTO;
import br.tec.itlabs.agendadorcadastrousuario.infrastructure.entity.Usuario;
import br.tec.itlabs.agendadorcadastrousuario.infrastructure.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioConverter usuarioConverter;

    public UsuarioDTO salvaUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuario = usuarioConverter.converteParaUsuario(usuarioDTO);
        return usuarioConverter.converteParaUsuarioDTO(usuarioRepository.save(usuario));
    }
}
