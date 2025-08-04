package br.tec.itlabs.agendadorcadastrousuario.business.service;

import br.tec.itlabs.agendadorcadastrousuario.business.converter.UsuarioConverter;
import br.tec.itlabs.agendadorcadastrousuario.business.dto.UsuarioDTO;
import br.tec.itlabs.agendadorcadastrousuario.infrastructure.entity.Usuario;
import br.tec.itlabs.agendadorcadastrousuario.infrastructure.exceptions.ConflictException;
import br.tec.itlabs.agendadorcadastrousuario.infrastructure.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioConverter usuarioConverter;
    private final PasswordEncoder passwordEncoder;

    public UsuarioDTO salvaUsuario(UsuarioDTO usuarioDTO) {
        emailExiste(usuarioDTO.getEmail());
        usuarioDTO.setSenha(passwordEncoder.encode(usuarioDTO.getSenha()));
        Usuario usuario = usuarioConverter.converteParaUsuario(usuarioDTO);
        return usuarioConverter.converteParaUsuarioDTO(usuarioRepository.save(usuario));
    }

    public void emailExiste(String email) {
        try {
            boolean existe = verificaEmailExistente(email);
            if (existe) {
                throw new ConflictException("Email já cadastrado " + email);
            }
        } catch (ConflictException e) {
            throw new ConflictException("Email já cadastrado ", e.getCause());
        }
    }

    public boolean verificaEmailExistente(String email) {

        return usuarioRepository.existsByEmail(email);
    }
}
