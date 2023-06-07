package com.insper.cuida_heliopolis.usuario;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.insper.cuida_heliopolis.avaliacao.AvaliaResponsavel;
import com.insper.cuida_heliopolis.avaliacao.Avaliacao;

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
    private double numAvaliacoes;
    
    @JsonIgnore
    @ManyToMany(mappedBy = "responsaveis")
    private List<Cuidador> cuidadores;

    @OneToMany(mappedBy = "responsavel")
    private List<AvaliaResponsavel> avaliacoes;

    public static double calculaNotaMedia(String tipo, List<Avaliacao> lista) {
        double nota = 0;
        double contador = (double) lista.size();

        List<AvaliaResponsavel> avaliacoes = new ArrayList<>();

        for (Avaliacao a : lista) {
            AvaliaResponsavel avaliacao = (AvaliaResponsavel) a;
            avaliacoes.add(avaliacao);
        }

        for (AvaliaResponsavel a : avaliacoes) {
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
