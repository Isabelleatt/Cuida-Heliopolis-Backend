package com.insper.cuida_heliopolis.cadastro;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class CadastroRequest {
    private String nome;
    private String cpf;
    private String email;
    private String senha;; 
}
