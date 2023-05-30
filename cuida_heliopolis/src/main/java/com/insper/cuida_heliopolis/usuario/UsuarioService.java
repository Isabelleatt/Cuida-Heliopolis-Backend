package com.insper.cuida_heliopolis.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService implements UserDetailsService{

    @Autowired
    private UsuarioRepository usuarioRepository;

    private final static String USER_NOT_FOUND_MSG = "Usuário com o email %s não encontrado";
    
    @Override 
    public UserDetails loadUserByUsername(String email) 
                throws UsernameNotFoundException {
            return usuarioRepository.findByEmail(email)
            .orElseThrow( () ->
            new UsernameNotFoundException(
                String.format(USER_NOT_FOUND_MSG, email)));
    }
}
