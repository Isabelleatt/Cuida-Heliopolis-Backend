package com.insper.cuida_heliopolis.verificado;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.insper.cuida_heliopolis.usuario.Cuidador;
import com.insper.cuida_heliopolis.usuario.Membro;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;
import java.time.LocalDateTime;



@Data
@Entity
public class Verificado {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "cuidador_id")
    private Cuidador cuidador;

    @ManyToOne
    @JoinColumn(name = "aprovador_id")
    private Membro aprovador;

    private LocalDateTime dataAprovacao;

    private VerificadoStatus status;

}
