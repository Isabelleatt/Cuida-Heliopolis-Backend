package com.insper.cuida_heliopolis.avaliacao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insper.cuida_heliopolis.avaliacao.dto.AvaliacaoCuidadoraReturnDTO;
import com.insper.cuida_heliopolis.avaliacao.dto.AvaliacaoCuidadoraSaveDTO;
import com.insper.cuida_heliopolis.avaliacao.dto.AvaliacaoResponsavelReturnDTO;
import com.insper.cuida_heliopolis.avaliacao.dto.AvaliacaoResponsavelSaveDTO;
import com.insper.cuida_heliopolis.usuario.Cuidador;
import com.insper.cuida_heliopolis.usuario.Responsavel;
import com.insper.cuida_heliopolis.usuario.UsuarioRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AvaliacaoService {

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public AvaliacaoCuidadoraReturnDTO salvaAvaliacaoCuidadora(AvaliacaoCuidadoraSaveDTO avaliacao, String email) {
        AvaliaCuidadora avaliaCuidadora = new AvaliaCuidadora();

        avaliaCuidadora.setAvaliadorEmail(avaliacao.getAvaliadorEmail());
        avaliaCuidadora.setAvaliadoEmail(email);
        avaliaCuidadora.setData(LocalDateTime.now());
        avaliaCuidadora.setTipo(AvaliacaoTipo.AVAL_CUIDADORA);

        avaliaCuidadora.setEspaco(avaliacao.getEspaco());
        avaliaCuidadora.setDisponibilidade(avaliacao.getDisponibilidade());
        avaliaCuidadora.setQualificacao(avaliacao.getQualificacao());
        avaliaCuidadora.setVinculo(avaliacao.getVinculo());
        avaliaCuidadora.setAtividades(avaliacao.getAtividades());
        avaliaCuidadora.setMediaAval((avaliacao.getAtividades() + avaliacao.getDisponibilidade() + avaliacao.getEspaco() + avaliacao.getQualificacao() + avaliacao.getVinculo()) / 5.0);

        avaliaCuidadora.setComentario(avaliacao.getComentario());

        avaliacaoRepository.save(avaliaCuidadora);

        Cuidador cuidador = (Cuidador) usuarioRepository.findByEmail(email).get();
        cuidador.getAvaliacoes().add(avaliaCuidadora);

        cuidador.setNotaMedia(cuidador.calculaNotaMedia("media"));
        cuidador.setDisponibilidadeMedia(cuidador.calculaNotaMedia("disponibilidade"));
        cuidador.setEspacoMedia(cuidador.calculaNotaMedia("espaço"));
        cuidador.setQualificacaoMedia(cuidador.calculaNotaMedia("qualificação"));
        cuidador.setVinculoMedia(cuidador.calculaNotaMedia("vínculo"));
        cuidador.setAtividadesMedia(cuidador.calculaNotaMedia("atividades"));
        cuidador.setNumAvaliacoes(cuidador.getNumAvaliacoes() + 1);
        usuarioRepository.save(cuidador);
        String anome = usuarioRepository.findByEmail(avaliacao.getAvaliadorEmail()).get().getNome();

        return AvaliacaoCuidadoraReturnDTO.convert(avaliaCuidadora, cuidador.getNome(), anome);


    }
    public AvaliacaoResponsavelReturnDTO salvaAvaliacaoResponsavel(AvaliacaoResponsavelSaveDTO avaliacao, String email) {
        AvaliaResponsavel avaliaResponsavel = new AvaliaResponsavel();

        avaliaResponsavel.setAvaliadorEmail(avaliacao.getAvaliadorEmail());
        avaliaResponsavel.setAvaliadoEmail(email);
        avaliaResponsavel.setData(LocalDateTime.now());
        avaliaResponsavel.setTipo(AvaliacaoTipo.AVAL_RESPONSAVEL);

        avaliaResponsavel.setComportamento(avaliacao.getComportamento());
        avaliaResponsavel.setPontualidade(avaliacao.getPontualidade());
        avaliaResponsavel.setPagamento(avaliacao.getPagamento());
        avaliaResponsavel.setMediaAval((avaliacao.getComportamento() + avaliacao.getPontualidade() + avaliacao.getPagamento()) / 3.0);

        avaliacaoRepository.save(avaliaResponsavel);

        Responsavel responsavel = (Responsavel) usuarioRepository.findByEmail(email).get();
        responsavel.getAvaliacoes().add(avaliaResponsavel);

        responsavel.setNotaMedia(responsavel.calculaNotaMedia("media"));
        responsavel.setComportamentoMedia(responsavel.calculaNotaMedia("comportamento"));
        responsavel.setPagamentoMedia(responsavel.calculaNotaMedia("pagamento"));
        responsavel.setPontualidadeMedia(responsavel.calculaNotaMedia("pontualidade"));
        responsavel.setNumAvaliacoes(responsavel.getNumAvaliacoes() + 1);
        usuarioRepository.save(responsavel);
        String anome = usuarioRepository.findByEmail(avaliacao.getAvaliadorEmail()).get().getNome();

        return AvaliacaoResponsavelReturnDTO.convert(avaliaResponsavel, responsavel.getNome(), anome);
    }

    public List<AvaliacaoCuidadoraReturnDTO> buscaAvaliacoesCuidadora(String email) {
        List<Avaliacao> avaliacoes = avaliacaoRepository.findByAvaliadoEmail(email);
        List<AvaliacaoCuidadoraReturnDTO> retorno = new ArrayList<AvaliacaoCuidadoraReturnDTO>();

        for (Avaliacao a : avaliacoes) {
            String nome = usuarioRepository.findByEmail(a.getAvaliadoEmail()).get().getNome();
            String anome = usuarioRepository.findByEmail(a.getAvaliadorEmail()).get().getNome();
            retorno.add(AvaliacaoCuidadoraReturnDTO.convert((AvaliaCuidadora) a, nome, anome));
        }

        return retorno;
    }

    public List<AvaliacaoResponsavelReturnDTO> buscaAvaliacoesResponsavel (String email) {
        List<Avaliacao> avaliacoes = avaliacaoRepository.findByAvaliadoEmail(email);
        List<AvaliacaoResponsavelReturnDTO> retorno = new ArrayList<AvaliacaoResponsavelReturnDTO>();

        for (Avaliacao a : avaliacoes) {
            String nome = usuarioRepository.findByEmail(a.getAvaliadoEmail()).get().getNome();
            String anome = usuarioRepository.findByEmail(a.getAvaliadorEmail()).get().getNome();
            retorno.add(AvaliacaoResponsavelReturnDTO.convert((AvaliaResponsavel) a, nome, anome));
        }

        return retorno;
    }

    // busca por id.
    public Avaliacao buscaAvaliacao(Integer id) {
        Avaliacao avaliacao = avaliacaoRepository.findById(id).get();
        if (avaliacao != null) {
            return avaliacao;
        }
        return null;
    }

    // deleta usando o id para isso.
    public void deletaAvaliacao(Integer id) {
        Avaliacao avaliacao = avaliacaoRepository.findById(id).get();
        if (avaliacao != null) {
            avaliacaoRepository.delete(avaliacao);
        }
    }
}
