package com.insper.cuida_heliopolis.avaliacao.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AvaliacaoCuidadoraSaveDTO {

    private String avaliadorEmail;

    private Integer espaco;

    private Integer disponibilidade;

    private Integer qualificacao;

    private Integer vinculo;

    private Integer atividades;

    private String comentario;

}
