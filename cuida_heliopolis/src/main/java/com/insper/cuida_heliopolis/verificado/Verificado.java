package com.insper.cuida_heliopolis.verificado;

import com.insper.cuida_heliopolis.usuario.Cuidador;
import com.insper.cuida_heliopolis.usuario.Membro;
import com.insper.cuida_heliopolis.usuario.Usuario;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
public class Verificado {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Cuidador cuidador;
    private Membro aprovador;
    private LocalDateTime dataAprovacao;

    private VerificadoStatus status;

}
