package com.insper.cuida_heliopolis.avaliacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insper.cuida_heliopolis.avaliacao.dto.AvaliacaoCuidadoraReturnDTO;
import com.insper.cuida_heliopolis.avaliacao.dto.AvaliacaoCuidadoraSaveDTO;
import com.insper.cuida_heliopolis.avaliacao.dto.AvaliacaoResponsavelReturnDTO;
import com.insper.cuida_heliopolis.avaliacao.dto.AvaliacaoResponsavelSaveDTO;
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
        avaliaCuidadora.setComentario(avaliacao.getComentario());
        avaliacaoRepository.save(avaliaCuidadora);
        String nome = usuarioRepository.findByEmail(email).get().getNome();

        return AvaliacaoCuidadoraReturnDTO.convert(avaliaCuidadora, nome);


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
        avaliacaoRepository.save(avaliaResponsavel);
        String nome = usuarioRepository.findByEmail(email).get().getNome();

        return AvaliacaoResponsavelReturnDTO.convert(avaliaResponsavel, nome);
    }

    public List<AvaliacaoCuidadoraReturnDTO> buscaAvaliacoesCuidadora(String email) {
        List<Avaliacao> avaliacoes = avaliacaoRepository.findByAvaliadoEmail(email);
        List<AvaliacaoCuidadoraReturnDTO> retorno = new ArrayList<AvaliacaoCuidadoraReturnDTO>();

        for (Avaliacao a : avaliacoes) {
            String nome = usuarioRepository.findByEmail(a.getAvaliadoEmail()).get().getNome();
            retorno.add(AvaliacaoCuidadoraReturnDTO.convert((AvaliaCuidadora) a, nome));
        }

        return retorno;
    }

    public List<AvaliacaoResponsavelReturnDTO> buscaAvaliacoesResponsavel (String email) {
        List<Avaliacao> avaliacoes = avaliacaoRepository.findByAvaliadoEmail(email);
        List<AvaliacaoResponsavelReturnDTO> retorno = new ArrayList<AvaliacaoResponsavelReturnDTO>();

        for (Avaliacao a : avaliacoes) {
            String nome = usuarioRepository.findByEmail(a.getAvaliadoEmail()).get().getNome();
            retorno.add(AvaliacaoResponsavelReturnDTO.convert((AvaliaResponsavel) a, nome));
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
