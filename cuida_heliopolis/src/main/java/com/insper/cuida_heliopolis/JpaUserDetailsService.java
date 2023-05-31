package com.insper.cuida_heliopolis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.insper.cuida_heliopolis.usuario.UsuarioRepository;
import com.insper.cuida_heliopolis.usuario.UsuarioSecurity;

@Service
public class JpaUserDetailsService implements UserDetailsService{

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return usuarioRepository.findByEmail(email).map(UsuarioSecurity::new).orElseThrow(() -> new UsernameNotFoundException("Usuario não encontrado: " + email));
    }
    
}
