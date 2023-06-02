package com.insper.cuida_heliopolis.auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.insper.cuida_heliopolis.config.JwtService;
import com.insper.cuida_heliopolis.usuario.UsuarioRepository;
import com.insper.cuida_heliopolis.usuario.dto.UsuarioSaveDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AuthenticationManager authManager;
    
    private final UsuarioRepository usuarioRepository;

    private final JwtService jwtService;

    public AuthenticationResponse authenticate(UsuarioLoginDTO user) {
        authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
        var u = usuarioRepository.findByEmail(user.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(u);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }    
}
