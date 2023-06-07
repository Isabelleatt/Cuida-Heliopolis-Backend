package com.insper.cuida_heliopolis.usuario.dto;

import com.insper.cuida_heliopolis.usuario.Cuidador;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CuidadorReturnDTO {
    private String nome;
    private String email;
    private String telefone;

    private String bio;
    private Integer numCriancas;
    private boolean interessado;
    private double notaMedia;
    private double espacoMedia;
    private double disponibilidadeMedia;
    private double qualificacaoMedia;
    private double vinculoMedia;
    private double atividadesMedia;
    private double numAvaliacoes;
    private boolean temVerificado;

    public static CuidadorReturnDTO convert(Cuidador cuidador, boolean rel) {
        CuidadorReturnDTO cuidadorReturnDTO = new CuidadorReturnDTO();

        cuidadorReturnDTO.setNome(cuidador.getNome());
        cuidadorReturnDTO.setEmail(cuidador.getEmail());
        cuidadorReturnDTO.setTelefone(cuidador.getTelefone());

        cuidadorReturnDTO.setBio(cuidador.getBio());
        cuidadorReturnDTO.setNumCriancas(cuidador.getNumCriancas());

        cuidadorReturnDTO.setNotaMedia(cuidador.getNotaMedia());
        cuidadorReturnDTO.setEspacoMedia(cuidador.getEspacoMedia());
        cuidadorReturnDTO.setDisponibilidadeMedia(cuidador.getDisponibilidadeMedia());
        cuidadorReturnDTO.setQualificacaoMedia(cuidador.getQualificacaoMedia());
        cuidadorReturnDTO.setVinculoMedia(cuidador.getVinculoMedia());
        cuidadorReturnDTO.setAtividadesMedia(cuidador.getAtividadesMedia());
        cuidadorReturnDTO.setInteressado(rel);

        cuidadorReturnDTO.setNumAvaliacoes(cuidador.getNumAvaliacoes());
        cuidadorReturnDTO.setTemVerificado(false);
        if (cuidador.getVerificado() != null) {
            if (cuidador.getVerificado().getStatus().toString().equals("ATIVO")){
                cuidadorReturnDTO.setTemVerificado(true);
            }
        }

        return cuidadorReturnDTO;
    }
}
