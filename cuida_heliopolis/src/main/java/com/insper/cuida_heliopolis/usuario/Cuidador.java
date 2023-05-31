package com.insper.cuida_heliopolis.usuario;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Cuidador extends Usuario{

    private String bio;
    
}
