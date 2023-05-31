package com.insper.cuida_heliopolis.usuario;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insper.cuida_heliopolis.usuario.dto.UsuarioReturnDTO;
import com.insper.cuida_heliopolis.usuario.dto.UsuarioSaveDTO;

@Service
public class UsuarioService{

    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioReturnDTO cadastro(UsuarioSaveDTO usuario, String tipo) {
        if (tipo == "MEMBRO") {
            Membro u = new Membro();

            u.setNome(usuario.getNome());
            u.setCpf(usuario.getCpf());
            u.setEmail(usuario.getEmail());
            u.setSenha(usuario.getSenha());
            u.setTipo(UsuarioTipo.MEMBRO);

            usuarioRepository.save(u);
        }
        else if (tipo == "CUIDADOR") {
            Cuidador u = new Cuidador();

            u.setNome(usuario.getNome());
            u.setCpf(usuario.getCpf());
            u.setEmail(usuario.getEmail());
            u.setSenha(usuario.getSenha());
            u.setTipo(UsuarioTipo.CUIDADOR);

            usuarioRepository.save(u);
        }
        else if (tipo == "RESPONSAVEL") {
            Responsavel u = new Responsavel();

            u.setNome(usuario.getNome());
            u.setCpf(usuario.getCpf());
            u.setEmail(usuario.getEmail());
            u.setSenha(usuario.getSenha());
            u.setTipo(UsuarioTipo.RESPONSAVEL);

            usuarioRepository.save(u);
        }
        return UsuarioReturnDTO.convert(usuario);
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

    public List<UsuarioReturnDTO> usuarios() {
        List<Usuario> us = usuarioRepository.findAll();
        List<UsuarioReturnDTO> usuarios = new ArrayList<UsuarioReturnDTO>();
        for (Usuario u : us) {
            usuarios.add(UsuarioReturnDTO.convert(u));
        }

        return usuarios;
    }
}
