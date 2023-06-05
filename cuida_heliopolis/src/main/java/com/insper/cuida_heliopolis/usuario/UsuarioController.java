package com.insper.cuida_heliopolis.usuario;

import java.util.List;

import com.insper.cuida_heliopolis.config.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @Autowired
    private JwtService jwtService;
    @GetMapping("/usuarios")
    public List<UsuarioReturnDTO> getUsuarios(@RequestHeader("Authorization") String authorizationHeader) {
        String authToken = authorizationHeader.substring("Bearer ".length());
        String name = jwtService.extractUsername(authToken);
        if (usuarioService.usuarioTipo(name).equals(UsuarioTipo.MEMBRO))
            return usuarioService.usuarios();
        return null;
    }
    @GetMapping("/cuidadores")
    public List<CuidadorReturnDTO> getCuidadores() {
        return usuarioService.cuidadores();
    }
    @GetMapping("/responsaveis")
    public List<ResponsavelReturnDTO> getResponsaveis(@RequestHeader("Authorization") String authorizationHeader) {
        String authToken = authorizationHeader.substring("Bearer ".length());
        String name = jwtService.extractUsername(authToken);
        if (usuarioService.usuarioTipo(name).equals(UsuarioTipo.MEMBRO)) {
            return usuarioService.responsaveis();
        }
        return null;
    }
    @GetMapping("/cuidador/{email}")
    public CuidadorReturnDTO getCuidador(@PathVariable String email) {
        return usuarioService.cuidador(email);
    }
    @GetMapping("/responsavel/{email}")
    public ResponsavelReturnDTO getResponsavel(@PathVariable String email) {
        return usuarioService.responsavel(email);
    }
    @PutMapping("/interesse/{email}")
    public void declararInteresse(@PathVariable String email, @RequestHeader("Authorization") String authorizationHeader) {
        String authToken = authorizationHeader.substring("Bearer ".length());
        String interessado = jwtService.extractUsername(authToken);
        usuarioService.declararInteresse(interessado,email);
    }
    @GetMapping("/relacionados/{email}")
    public List<UsuarioReturnDTO> getUsuariosRelacionados(@PathVariable String email) {
        return usuarioService.relacionados(email);
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
