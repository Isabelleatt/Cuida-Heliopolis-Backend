package com.insper.cuida_heliopolis.avaliacao;

import org.antlr.v4.runtime.misc.NotNull;

import com.insper.cuida_heliopolis.usuario.Usuario;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;

@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
public class Avaliacao {
    
    @Column(name = "identifier", nullable = false, unique = true)
    private String identifier;
    @NotNull
    protected Integer avaliador_id;
    @NotNull
    protected Integer avaliado_id;
    protected Time data;
}
