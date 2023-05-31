package com.insper.cuida_heliopolis.usuario;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.insper.cuida_heliopolis.verificado.Verificado;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Membro extends Usuario{

    @JsonIgnore
    @OneToMany(mappedBy = "aprovador")
    private List<Verificado> verificados;
    
}
