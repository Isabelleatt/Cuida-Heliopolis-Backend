package com.insper.cuida_heliopolis.usuario;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.insper.cuida_heliopolis.usuario.dto.UsuarioReturnDTO;
import com.insper.cuida_heliopolis.usuario.dto.UsuarioSaveDTO;

@RestController
@RequestMapping("")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public List<UsuarioReturnDTO> getUsuarios() {
        return usuarioService.usuarios();
    }
    
    @PostMapping("/usuario/{tipo}")
    public UsuarioReturnDTO cadastraUsuario(@RequestBody UsuarioSaveDTO usuario, @PathVariable String tipo) {
        return usuarioService.cadastro(usuario, tipo);
    }

    @PutMapping("/usuario")
    public UsuarioReturnDTO alterarUsuario(@RequestBody UsuarioSaveDTO usuario, @PathVariable String email) {
        return usuarioService.alterar(usuario, email);
    }
}
