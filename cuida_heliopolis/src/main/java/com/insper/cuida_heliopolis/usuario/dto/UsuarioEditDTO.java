package com.insper.cuida_heliopolis.usuario.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioEditDTO {
    private String nome;
    private String telefone;
    private String email;
    private String senha;
    private String bio;
    private Integer numCriancas;
}
