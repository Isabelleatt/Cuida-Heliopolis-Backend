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
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY) // jakarta automates int ids
    protected Integer id;
    
    @Column
    @NotNull
    protected Integer avaliadorId;
    @NotNull
    protected Integer avaliadoId;
    protected String data;
}
