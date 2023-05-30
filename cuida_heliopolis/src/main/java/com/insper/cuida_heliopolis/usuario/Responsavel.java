package com.insper.cuida_heliopolis.usuario;

import java.util.List;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Responsavel extends Usuario{
    private List<Cuidador> cuidadores;
}
