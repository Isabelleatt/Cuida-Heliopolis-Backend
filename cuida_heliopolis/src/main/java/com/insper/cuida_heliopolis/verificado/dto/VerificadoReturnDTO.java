package com.insper.cuida_heliopolis.verificado.dto;

import java.time.LocalDateTime;

import com.insper.cuida_heliopolis.verificado.Verificado;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VerificadoReturnDTO {
    
    private String aprovador_email;
    private String cuidador_email;
    private LocalDateTime dataAprovacao; 

    public static VerificadoReturnDTO convert(Verificado verificado) {
        VerificadoReturnDTO v = new VerificadoReturnDTO();
        v.setAprovador_email(verificado.getAprovador_email());
        v.setCuidador_email(verificado.getCuidador_email());
        v.setDataAprovacao(verificado.getDataAprovacao());
        return v;
    }
}
