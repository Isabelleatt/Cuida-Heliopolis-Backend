package com.insper.cuida_heliopolis.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.insper.cuida_heliopolis.usuario.dto.UsuarioLoginDTO;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService service;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody UsuarioLoginDTO user) {
        return ResponseEntity.ok(service.authenticate(user));
    }
}
