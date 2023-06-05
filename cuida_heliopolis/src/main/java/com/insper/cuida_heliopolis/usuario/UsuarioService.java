package com.insper.cuida_heliopolis.usuario;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.insper.cuida_heliopolis.auth.AuthenticationResponse;
import com.insper.cuida_heliopolis.config.JwtService;
import com.insper.cuida_heliopolis.usuario.dto.UsuarioReturnDTO;
import com.insper.cuida_heliopolis.usuario.dto.UsuarioSaveDTO;

@Service
public class UsuarioService{
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;

    public AuthenticationResponse cadastro(UsuarioSaveDTO usuario, String tipo) {
        Usuario u = null;

        if (tipo.equals("MEMBRO")) {
            u = new Membro();

            u.setNome(usuario.getNome());
            u.setCpf(usuario.getCpf());
            u.setEmail(usuario.getEmail());
            u.setSenha(passwordEncoder.encode(usuario.getSenha()));
            u.setTipo(UsuarioTipo.MEMBRO);
        }
        else if (tipo.equals("CUIDADOR")) {
            u = new Cuidador();

            u.setNome(usuario.getNome());
            u.setCpf(usuario.getCpf());
            u.setEmail(usuario.getEmail());
            u.setSenha(passwordEncoder.encode(usuario.getSenha()));
            u.setTipo(UsuarioTipo.CUIDADOR);
        }
        else if (tipo.equals("RESPONSAVEL")) {
            u = new Responsavel();

            u.setNome(usuario.getNome());
            u.setCpf(usuario.getCpf());
            u.setEmail(usuario.getEmail());
            u.setSenha(passwordEncoder.encode(usuario.getSenha()));
            u.setTipo(UsuarioTipo.RESPONSAVEL);            
        }
        usuarioRepository.save(u);
        var jwtToken = jwtService.generateToken(u);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }
    public UsuarioReturnDTO alterar(UsuarioSaveDTO usuario, String email) {
        Usuario u = usuarioRepository.findByEmail(email).get();
        if (u != null) {
            if (usuario.getNome() != null) {
                u.setNome(usuario.getNome());
            }

            if (usuario.getCpf() != null) {
                u.setCpf(usuario.getCpf());
            }

            if (usuario.getEmail() != null) {
                u.setEmail(usuario.getEmail());
            }

            if (usuario.getSenha() != null) {
                u.setSenha(usuario.getSenha());
            }
            usuarioRepository.save(u);
            return UsuarioReturnDTO.convert(usuario);
        }
        return null;
    }
    public List<UsuarioReturnDTO> usuariosRelacionados(String email) {
        Usuario usuario = usuarioRepository.findByEmail(email).get();
        List<UsuarioReturnDTO> relacionados = new ArrayList<>();
        if (usuario.getTipo().equals(UsuarioTipo.RESPONSAVEL)) {
            Responsavel responsavel = (Responsavel) usuario;
            List<Cuidador> cuidadores = responsavel.getCuidadores();
            for (Cuidador c : cuidadores) {
                relacionados.add(UsuarioReturnDTO.convert(c));
            }
        } else {
            Cuidador cuidador = (Cuidador) usuario;
            List<Responsavel> responsaveis = cuidador.getResponsaveis();

            for (Responsavel r : responsaveis) {
                relacionados.add(UsuarioReturnDTO.convert(r));
            }
        }
        return relacionados;

    }
    public List<UsuarioReturnDTO> usuarios() {
        List<Usuario> us = usuarioRepository.findAll();
        List<UsuarioReturnDTO> usuarios = new ArrayList<UsuarioReturnDTO>();
        for (Usuario u : us) {
            usuarios.add(UsuarioReturnDTO.convert(u));
        }
        return usuarios;
    }
}
