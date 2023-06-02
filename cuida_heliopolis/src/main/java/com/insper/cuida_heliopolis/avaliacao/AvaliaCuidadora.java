package com.insper.cuida_heliopolis.avaliacao;

import com.insper.cuida_heliopolis.usuario.Cuidador;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class AvaliaCuidadora extends Avaliacao {
    private Integer espaco;
    private Integer disponibilidade;
    private Integer qualificacao;
    private Integer vinculo;
    private Integer atividades;
    private String comentario;

    @ManyToOne
    @JoinColumn(name = "cuidador_id")
    private Cuidador cuidador;
}
