package com.insper.cuida_heliopolis.avaliacao.dto;

import java.time.LocalDateTime;
import com.insper.cuida_heliopolis.avaliacao.AvaliaCuidadora;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AvaliacaoCuidadoraReturnDTO {

    private String nome;
    private LocalDateTime data;

    private Integer espaco;
    private Integer disponibilidade;
    private Integer qualificacao;
    private Integer vinculo;
    private Integer atividades;
    private double mediaAval;
    
    private String comentario;

    public static AvaliacaoCuidadoraReturnDTO convert(AvaliaCuidadora avaliacao, String nome) {

        AvaliacaoCuidadoraReturnDTO retorno = new AvaliacaoCuidadoraReturnDTO();
        retorno.setNome(nome);
        retorno.setData(avaliacao.getData());

        retorno.setEspaco(avaliacao.getEspaco());
        retorno.setDisponibilidade(avaliacao.getDisponibilidade());
        retorno.setQualificacao(avaliacao.getQualificacao());
        retorno.setVinculo(avaliacao.getVinculo());
        retorno.setAtividades(avaliacao.getAtividades());
        retorno.setMediaAval(avaliacao.getMediaAval());

        retorno.setComentario(avaliacao.getComentario());

        return retorno;
    }
}
