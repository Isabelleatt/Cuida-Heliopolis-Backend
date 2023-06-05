package com.insper.cuida_heliopolis.avaliacao;

import com.insper.cuida_heliopolis.config.JwtService;
import com.insper.cuida_heliopolis.usuario.UsuarioService;
import com.insper.cuida_heliopolis.usuario.UsuarioTipo;
import org.antlr.v4.runtime.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.insper.cuida_heliopolis.avaliacao.dto.AvaliacaoCuidadoraReturnDTO;
import com.insper.cuida_heliopolis.avaliacao.dto.AvaliacaoCuidadoraSaveDTO;
import com.insper.cuida_heliopolis.avaliacao.dto.AvaliacaoResponsavelReturnDTO;
import com.insper.cuida_heliopolis.avaliacao.dto.AvaliacaoResponsavelSaveDTO;

import java.util.List;

@RestController
@RequestMapping("/avaliacao")
public class AvaliacaoController {
    @Autowired
    private AvaliacaoService avaliacaoService;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/cuidadora/{email}")
    public List<AvaliacaoCuidadoraReturnDTO> listaAvaliacoesCuidadora(@PathVariable String email) {
        return avaliacaoService.buscaAvaliacoesCuidadora(email);
    }

    @GetMapping("/responsavel/{email}")
    public List<AvaliacaoResponsavelReturnDTO> listaAvaliacoesResponsavel(@PathVariable String email) {
        return avaliacaoService.buscaAvaliacoesResponsavel(email);
    }

    @PostMapping("/cuidadora/{email}")
    public AvaliacaoCuidadoraReturnDTO salvarAvaliacaoCuidadora(@RequestBody AvaliacaoCuidadoraSaveDTO avaliacao, @PathVariable String email, @RequestHeader("Authorization") String authorizationHeader) {
        String authToken = authorizationHeader.substring("Bearer ".length());
        String avaliadorEmail = jwtService.extractUsername(authToken);

        if (email.equals(avaliadorEmail)) {
            return null;
        }
        // verifica se a avaliação é legal.
        if (usuarioService.usuarioTipo(email).equals(usuarioService.usuarioTipo(avaliadorEmail))) {
            return null;
        }

        avaliacao.setAvaliadorEmail(avaliadorEmail);
        return avaliacaoService.salvaAvaliacaoCuidadora(avaliacao, email);
    }
    @PostMapping("/responsavel/{email}")
    public AvaliacaoResponsavelReturnDTO salvarAvaliacaoResponsavel(@RequestBody AvaliacaoResponsavelSaveDTO avaliacao, @PathVariable String email, @RequestHeader("Authorization") String authorizationHeader) {
        String authToken = authorizationHeader.substring("Bearer ".length());
        String avaliadorEmail = jwtService.extractUsername(authToken);

        if (email.equals(avaliadorEmail)) {
            return null;
        }
        // verifica se a avaliação é legal.
        if (usuarioService.usuarioTipo(email).equals(usuarioService.usuarioTipo(avaliadorEmail))) {
            return null;
        }

        avaliacao.setAvaliadorEmail(avaliadorEmail);
        return avaliacaoService.salvaAvaliacaoResponsavel(avaliacao, email);
    }

    @DeleteMapping("/cuidadora/{id}")
    public  Integer deletaAvaliacaoCuidadora(@PathVariable Integer id, @RequestHeader("Authorization") String authorizationHeader) {
        String authToken = authorizationHeader.substring("Bearer ".length());
        String email = jwtService.extractUsername(authToken);
        if (email.equals( avaliacaoService.buscaAvaliacao(id).getAvaliadorEmail()) || usuarioService.usuario(email).equals(UsuarioTipo.MEMBRO)) {
            avaliacaoService.deletaAvaliacao(id);
            return id;
        }
        return -1;
    }
    @DeleteMapping("/responsavel/{id}")
    public Integer deletaAvaliacaoResponsavel(@PathVariable Integer id, @RequestHeader("Authorization") String authorizationHeader) {
        String authToken = authorizationHeader.substring("Bearer ".length());
        String email = jwtService.extractUsername(authToken);
        if (email.equals( avaliacaoService.buscaAvaliacao(id).getAvaliadorEmail()) || usuarioService.usuario(email).equals(UsuarioTipo.MEMBRO)) {
            avaliacaoService.deletaAvaliacao(id);
            return id;
        }
        return -1;
    }
}
