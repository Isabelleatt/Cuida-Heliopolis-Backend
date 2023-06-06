package com.insper.cuida_heliopolis.usuario.dto;

import com.insper.cuida_heliopolis.usuario.Responsavel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponsavelReturnDTO {
    private String nome;
    private String email;
    private String telefone;

    private double notaMedia;
    private double comportamentoMedia;
    private double pontualidadeMedia;
    private double pagamentoMedia;
    private double numAvaliacoes;


    public static ResponsavelReturnDTO convert(Responsavel responsavel) {
        ResponsavelReturnDTO responsavelReturnDTO = new ResponsavelReturnDTO();

        responsavelReturnDTO.setNome(responsavel.getNome());
        responsavelReturnDTO.setTelefone(responsavel.getTelefone());
        responsavelReturnDTO.setEmail(responsavel.getEmail());
        
        responsavelReturnDTO.setNotaMedia(responsavel.getNotaMedia());
        responsavelReturnDTO.setComportamentoMedia(responsavel.getComportamentoMedia());
        responsavelReturnDTO.setPontualidadeMedia(responsavel.getPontualidadeMedia());
        responsavelReturnDTO.setPagamentoMedia(responsavel.getPagamentoMedia());
        responsavelReturnDTO.setNumAvaliacoes(responsavel.getNumAvaliacoes());

        return responsavelReturnDTO;
    }
}
