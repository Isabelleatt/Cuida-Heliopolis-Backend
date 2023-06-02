package com.insper.cuida_heliopolis.avaliacao;

import com.insper.cuida_heliopolis.usuario.Responsavel;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Entity
@NoArgsConstructor
public class AvaliaResponsavel extends Avaliacao {
    private Integer comportamento;
    private Integer pontualidade;
    private Integer pagamento;
    @ManyToOne
    @JoinColumn(name = "responsavel_id")
    private Responsavel responsavel;
}
