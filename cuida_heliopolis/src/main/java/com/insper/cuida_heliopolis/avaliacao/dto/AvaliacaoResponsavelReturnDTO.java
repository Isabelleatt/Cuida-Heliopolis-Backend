package com.insper.cuida_heliopolis.avaliacao.dto;

import java.time.LocalDateTime;

import com.insper.cuida_heliopolis.avaliacao.AvaliaResponsavel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AvaliacaoResponsavelReturnDTO {
    
    private String nome;
    private LocalDateTime data;

    private Integer comportamento;
    private Integer pontualidade;
    private Integer pagamento;
    private double mediaAval;

    public static AvaliacaoResponsavelReturnDTO convert(AvaliaResponsavel avaliacao, String nome) {

        AvaliacaoResponsavelReturnDTO retorno = new AvaliacaoResponsavelReturnDTO();
        retorno.setNome(nome);
        retorno.setData(avaliacao.getData());

        retorno.setComportamento(avaliacao.getComportamento());
        retorno.setPontualidade(avaliacao.getPontualidade());
        retorno.setPagamento(avaliacao.getPagamento());
        retorno.setMediaAval(avaliacao.getMediaAval());

        return retorno;
    }
}
