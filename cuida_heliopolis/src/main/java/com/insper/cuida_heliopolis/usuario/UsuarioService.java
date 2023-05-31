package com.insper.cuida_heliopolis.usuario;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService{

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario cadastro(Usuario usuario) {
        usuarioRepository.save(usuario);
        return usuario;
    }

    public List<Usuario> usuarios() {
        return usuarioRepository.findAll();
    }
}
