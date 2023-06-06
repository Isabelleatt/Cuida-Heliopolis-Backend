package com.insper.cuida_heliopolis.usuario;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.insper.cuida_heliopolis.avaliacao.AvaliaCuidadora;
import com.insper.cuida_heliopolis.verificado.Verificado;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
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
    private Integer numCriancas;

    private double notaMedia;
    private double espacoMedia;
    private double disponibilidadeMedia;
    private double qualificacaoMedia;
    private double vinculoMedia;
    private double atividadesMedia;
    private double numAvaliacoes;
    
    @JsonIgnore
    @OneToOne(mappedBy = "cuidador")
    private Verificado verificado;

    @ManyToMany
    @JoinTable(name = "cuidadores_responsaveis")
    private List<Responsavel> responsaveis;

    @OneToMany(mappedBy = "cuidador")
    private List<AvaliaCuidadora> avaliacoes;

    public double calculaNotaMedia(String tipo) {
        double nota = 0;
        double contador = (double) this.avaliacoes.size();
        for (AvaliaCuidadora a : this.avaliacoes) {
            if (tipo.equals("espaço")) {
                nota += a.getEspaco();
            }
            else if (tipo.equals("disponibilidade")) {
                nota += a.getDisponibilidade();
            }
            else if (tipo.equals("qualificação")) {
                nota += a.getQualificacao();
            }
            else if (tipo.equals("vínculo")) {
                nota += a.getVinculo();
            }
            else if (tipo.equals("atividades")) {
                nota += a.getAtividades();
            }
            else if (tipo.equals("media")) {
                nota += a.getMediaAval();
            }

        }

        return nota/contador;
    }
}
