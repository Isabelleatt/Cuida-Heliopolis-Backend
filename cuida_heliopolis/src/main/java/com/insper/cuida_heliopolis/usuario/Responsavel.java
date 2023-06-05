package com.insper.cuida_heliopolis.usuario;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.insper.cuida_heliopolis.avaliacao.AvaliaResponsavel;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Responsavel extends Usuario{

    private double notaMedia;
    private double comportamentoMedia;
    private double pontualidadeMedia;
    private double pagamentoMedia;
    
    @JsonIgnore
    @ManyToMany(mappedBy = "responsaveis")
    private List<Cuidador> cuidadores;

    @OneToMany(mappedBy = "responsavel")
    private List<AvaliaResponsavel> avaliacoes;

    public double calculaNotaMedia(String tipo) {
        double nota = 0;
        double contador = (double) this.avaliacoes.size();
        for (AvaliaResponsavel a : this.avaliacoes) {
            if (tipo.equals("comportamento")) {
                nota += a.getComportamento();
            }
            else if (tipo.equals("pontualidade")) {
                nota += a.getPontualidade();
            }
            else if (tipo.equals("pagamento")) {
                nota += a.getPagamento();
            }
            else if (tipo.equals("media")) {
                nota += a.getMediaAval();
            }

        }

        return nota/contador;
    }
}
