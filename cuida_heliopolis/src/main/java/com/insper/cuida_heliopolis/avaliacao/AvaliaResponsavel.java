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
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY) // jakarta automates int ids
    private Integer id;
    @Column
    private  Integer comportamento;
    private  Integer pontualidade;
    private Integer pagamento;

    public AvaliaResponsavel (Time data, Integer avaliador_id, Integer avaliado_id) {
        this.avaliador_id = avaliador_id;
        this.avaliado_id = avaliado_id;
        this.data = data;
    }
    
}
