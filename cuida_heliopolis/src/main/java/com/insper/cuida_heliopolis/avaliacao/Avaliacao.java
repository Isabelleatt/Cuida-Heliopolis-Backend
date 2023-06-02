package com.insper.cuida_heliopolis.avaliacao;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@NoArgsConstructor
public class Avaliacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;
    protected String avaliadorEmail;
    protected String avaliadoEmail;

    protected LocalDateTime data;
    protected AvaliacaoTipo tipo;
}
