package com.insper.cuida_heliopolis.usuario;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.insper.cuida_heliopolis.verificado.Verificado;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Cuidador extends Usuario{

    private String bio;
    
    @JsonIgnore
    @OneToOne(mappedBy = "cuidador")
    private Verificado verificado;

    @ManyToMany
    @JoinTable(name = "cuidadores_responsaveis")
    private List<Responsavel> responsaveis;

}
