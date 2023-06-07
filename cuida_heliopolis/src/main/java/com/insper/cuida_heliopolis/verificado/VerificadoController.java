package com.insper.cuida_heliopolis.verificado;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.insper.cuida_heliopolis.config.JwtService;
import com.insper.cuida_heliopolis.usuario.UsuarioService;
import com.insper.cuida_heliopolis.usuario.UsuarioTipo;
import com.insper.cuida_heliopolis.verificado.dto.VerificadoReturnDTO;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/verificado")
public class VerificadoController {

    @Autowired
    private VerificadoService verificadoService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/{email}")
    public Boolean checaValidade(@PathVariable String email) {
        return verificadoService.checaValidade(email);
    }

    @PostMapping("/{email_cuidador}")
    public VerificadoReturnDTO cadastraVerificado(@RequestHeader("Authorization") String authorizationHeader, @PathVariable String email_cuidador) {
        String authToken = authorizationHeader.substring("Bearer ".length());
        String name = jwtService.extractUsername(authToken);
        if (usuarioService.usuarioTipo(name).equals(UsuarioTipo.MEMBRO)) {
            return verificadoService.adicionarVerificado(email_cuidador, name);
        }
        return null;
    }

    @PutMapping("/{email}")
    public void alteraStatus(@PathVariable String email) {
        verificadoService.alteraStatus(email);
    }

    @DeleteMapping("/{email_cuidador}")
    public void removerVerificado(@RequestHeader("Authorization") String authorizationHeader, @PathVariable String email_cuidador) {
        String authToken = authorizationHeader.substring("Bearer ".length());
        String name = jwtService.extractUsername(authToken);
        if (usuarioService.usuarioTipo(name).equals(UsuarioTipo.MEMBRO)) {
            verificadoService.removerVerificado(email_cuidador);
        }
        return;
    }
}
