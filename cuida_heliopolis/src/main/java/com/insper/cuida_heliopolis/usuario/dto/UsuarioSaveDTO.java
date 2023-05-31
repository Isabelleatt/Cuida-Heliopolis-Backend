package com.insper.cuida_heliopolis.usuario.dto;

import com.insper.cuida_heliopolis.usuario.Usuario;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioSaveDTO {
    private String nome;
    private String cpf;
    private String email;
    private String senha;
}
