package com.insper.cuida_heliopolis.avaliacao;

import com.insper.cuida_heliopolis.avaliacao.Avaliacao;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Time;

@Getter
@Setter
@Entity
public class AvaliaResponsavel extends Avaliacao {
    @Column
    private  Integer comportamento;
    private  Integer pontualidade;
    private Integer pagamento;

    public AvaliaResponsavel (Time data, Integer avaliador_id, Integer avaliado_id, Integer id) {
        this.id = id;
        this.avaliadorId = avaliador_id;
        this.avaliadoId = avaliado_id;
        this.data = data;
    }
    
}
