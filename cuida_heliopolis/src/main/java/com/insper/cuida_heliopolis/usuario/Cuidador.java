package com.insper.cuida_heliopolis.usuario;

import java.util.List;

import com.insper.cuida_heliopolis.avaliacao.Avaliacao;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Cuidador extends Usuario {
    private List<Avaliacao> avaliacoes;
}
