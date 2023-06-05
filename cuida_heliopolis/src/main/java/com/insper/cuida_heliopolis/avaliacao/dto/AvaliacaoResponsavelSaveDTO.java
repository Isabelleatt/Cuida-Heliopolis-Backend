package com.insper.cuida_heliopolis.avaliacao.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AvaliacaoResponsavelSaveDTO {
    
    private String avaliadorEmail;

    private Integer comportamento;

    private Integer pontualidade;

    private Integer pagamento;
}
