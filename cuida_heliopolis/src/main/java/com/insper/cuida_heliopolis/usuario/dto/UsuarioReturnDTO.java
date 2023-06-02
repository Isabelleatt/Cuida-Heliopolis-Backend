package com.insper.cuida_heliopolis.usuario.dto;

import com.insper.cuida_heliopolis.usuario.Usuario;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioReturnDTO {

    private String nome;
    private String email;

    public static UsuarioReturnDTO convert(UsuarioSaveDTO usuario) {
        UsuarioReturnDTO userReturnDTO = new UsuarioReturnDTO();
        userReturnDTO.setNome(usuario.getNome());
        userReturnDTO.setEmail(usuario.getEmail());
        return userReturnDTO;
    }

    public static UsuarioReturnDTO convert(Usuario usuario) {
        UsuarioReturnDTO userReturnDTO = new UsuarioReturnDTO();
        userReturnDTO.setNome(usuario.getNome());
        userReturnDTO.setEmail(usuario.getEmail());
        return userReturnDTO;
    }
}