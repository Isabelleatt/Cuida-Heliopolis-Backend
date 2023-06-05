package com.insper.cuida_heliopolis.usuario;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.insper.cuida_heliopolis.auth.AuthenticationResponse;
import com.insper.cuida_heliopolis.usuario.dto.CuidadorReturnDTO;
import com.insper.cuida_heliopolis.usuario.dto.ResponsavelReturnDTO;
import com.insper.cuida_heliopolis.usuario.dto.UsuarioReturnDTO;
import com.insper.cuida_heliopolis.usuario.dto.UsuarioSaveDTO;

@RestController
@RequestMapping("")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/usuario")
    public List<UsuarioReturnDTO> getUsuarios() {
        return usuarioService.usuarios();
    }

    @GetMapping("/cuidador/{email}")
    public CuidadorReturnDTO getCuidador(@PathVariable String email) {
        return usuarioService.cuidador(email);
    }

    @GetMapping("/responsavel/{email}")
    public ResponsavelReturnDTO getResponsavel(@PathVariable String email) {
        return usuarioService.responsavel(email);
    }

    @PostMapping("/api/auth/cadastro/{tipo}")
    public ResponseEntity<AuthenticationResponse> cadastraUsuario(@RequestBody UsuarioSaveDTO usuario, @PathVariable String tipo) {
        return ResponseEntity.ok(usuarioService.cadastro(usuario, tipo));
    }

    @PutMapping("/usuario/{email}")
    public UsuarioReturnDTO alterarUsuario(@RequestBody UsuarioSaveDTO usuario, @PathVariable String email) {
        return usuarioService.alterar(usuario, email);
    }

}
