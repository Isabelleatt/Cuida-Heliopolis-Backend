package com.insper.cuida_heliopolis.avaliacao;

import com.insper.cuida_heliopolis.avaliacao.Avaliacao;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Time;

@Getter
@Setter
@Entity
public class AvaliaCuidadora extends Avaliacao {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY) // jakarta automates int ids
    private Integer id;
    @Column
    private Integer espaco;
    private Integer disponibilidade;
    private Integer qualificacao;
    private Integer vinculo;
    private Integer atividades;
    private String comentario;
    public AvaliaCuidadora (Time data, Integer avaliador_id, Integer avaliado_id) {
        this.avaliador_id = avaliador_id;
        this.avaliado_id = avaliado_id;
        this.data = data;
    }

}
