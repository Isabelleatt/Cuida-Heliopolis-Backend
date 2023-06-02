package com.insper.cuida_heliopolis.usuario;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Responsavel extends Usuario{
    
    @JsonIgnore
    @ManyToMany(mappedBy = "responsaveis")
    private List<Cuidador> cuidadores;

}
