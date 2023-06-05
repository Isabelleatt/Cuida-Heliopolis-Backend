package com.insper.cuida_heliopolis.usuario.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioSaveDTO {
    private String nome;
    private String telefone;
    private String email;
    private String senha;
}
