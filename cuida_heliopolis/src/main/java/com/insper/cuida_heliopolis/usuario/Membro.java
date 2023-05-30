package com.insper.cuida_heliopolis.usuario;

import java.util.List;

import com.insper.cuida_heliopolis.verificado.Verificado;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Membro extends Usuario {

    private List<Verificado> verificados;

}
