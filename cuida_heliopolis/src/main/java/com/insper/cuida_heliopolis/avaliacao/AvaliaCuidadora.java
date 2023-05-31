package com.insper.cuida_heliopolis.avaliacao;

import com.insper.cuida_heliopolis.avaliacao.Avaliacao;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class AvaliaCuidadora extends Avaliacao {
    @Column
    private Integer espaco;
    private Integer disponibilidade;
    private Integer qualificacao;
    private Integer vinculo;
    private Integer atividades;
    private String comentario;
}
