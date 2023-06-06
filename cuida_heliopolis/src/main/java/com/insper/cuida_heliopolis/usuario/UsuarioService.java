package com.insper.cuida_heliopolis.usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.insper.cuida_heliopolis.auth.AuthenticationResponse;
import com.insper.cuida_heliopolis.config.JwtService;
import com.insper.cuida_heliopolis.usuario.dto.CuidadorReturnDTO;
import com.insper.cuida_heliopolis.usuario.dto.ResponsavelReturnDTO;
import com.insper.cuida_heliopolis.usuario.dto.UsuarioEditDTO;
import com.insper.cuida_heliopolis.usuario.dto.UsuarioReturnDTO;
import com.insper.cuida_heliopolis.usuario.dto.UsuarioSaveDTO;

@Service
public class UsuarioService{
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;

    public AuthenticationResponse cadastro(UsuarioSaveDTO usuario, String tipo) {
        Usuario u = null;

        if (tipo.equals("MEMBRO")) {
            u = new Membro();

            u.setNome(usuario.getNome());
            u.setTelefone(usuario.getTelefone());
            u.setEmail(usuario.getEmail());
            u.setSenha(passwordEncoder.encode(usuario.getSenha()));
            u.setTipo(UsuarioTipo.MEMBRO);
        }
        else if (tipo.equals("CUIDADOR")) {
            u = new Cuidador();

            u.setNome(usuario.getNome());
            u.setTelefone(usuario.getTelefone());
            u.setEmail(usuario.getEmail());
            u.setSenha(passwordEncoder.encode(usuario.getSenha()));
            u.setTipo(UsuarioTipo.CUIDADOR);
        }
        else if (tipo.equals("RESPONSAVEL")) {
            u = new Responsavel();

            u.setNome(usuario.getNome());
            u.setTelefone(usuario.getTelefone());
            u.setEmail(usuario.getEmail());
            u.setSenha(passwordEncoder.encode(usuario.getSenha()));
            u.setTipo(UsuarioTipo.RESPONSAVEL);            
        }
        usuarioRepository.save(u);
        var jwtToken = jwtService.generateToken(u);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }
    public UsuarioReturnDTO alterar(UsuarioEditDTO usuario, String email) {
        Usuario u = (Usuario) usuarioRepository.findByEmail(email).get();
        if (u.getTipo().equals("CUIDADOR")) {
            Cuidador c = (Cuidador) usuarioRepository.findByEmail(email).get();
            if (usuario.getBio() != null) {c.setBio(usuario.getBio());}
            if (usuario.getNumCriancas() != null) {c.setNumCriancas(usuario.getNumCriancas());}
            if (usuario.getNome() != null) {c.setNome(usuario.getNome());}
            if (usuario.getSenha() != null) {c.setSenha(usuario.getSenha());}
            if (usuario.getEmail() != null) {c.setEmail(usuario.getEmail());}
            if (usuario.getTelefone() != null) {c.setTelefone(usuario.getTelefone());}
            usuarioRepository.save(c);
            return UsuarioReturnDTO.convert(u);
        }

        if (u != null) {
            if (usuario.getNome() != null) { u.setNome(usuario.getNome());}
            if (usuario.getEmail() != null) {u.setEmail(usuario.getEmail());}
            if (usuario.getSenha() != null) {u.setSenha(usuario.getSenha());}
            if (usuario.getTelefone() != null) {u.setTelefone(usuario.getTelefone());}
            usuarioRepository.save(u);
            return UsuarioReturnDTO.convert(u);
        }
        return null;
    }


    public List<UsuarioReturnDTO> usuariosRelacionados(String email) {
        Usuario usuario = usuarioRepository.findByEmail(email).get();
        List<UsuarioReturnDTO> relacionados = new ArrayList<>();
        if (usuario.getTipo().equals(UsuarioTipo.RESPONSAVEL)) {
            Responsavel responsavel = (Responsavel) usuario;
            List<Cuidador> cuidadores = responsavel.getCuidadores();
            for (Cuidador c : cuidadores) {
                relacionados.add(UsuarioReturnDTO.convert(c));
            }
        } else {
            Cuidador cuidador = (Cuidador) usuario;
            List<Responsavel> responsaveis = cuidador.getResponsaveis();

            for (Responsavel r : responsaveis) {
                relacionados.add(UsuarioReturnDTO.convert(r));
            }
        }
        return relacionados;

    }
    public void declararInteresse(String interessado, String interessante) {
        Usuario perfilInteressado = usuarioRepository.findByEmail(interessado).get();
        Usuario perfilInteressante = usuarioRepository.findByEmail(interessante).get();

        if (perfilInteressado.getTipo().equals(UsuarioTipo.RESPONSAVEL)) {
            Responsavel responsavel  = (Responsavel) perfilInteressado;
            Cuidador cuidador = (Cuidador) perfilInteressante;

            for (Responsavel r:cuidador.getResponsaveis()) {
                if (r.getEmail().equals(interessado)) {
                    return;
                }
            }
            responsavel.getCuidadores().add(cuidador);
            cuidador.getResponsaveis().add(responsavel);

            usuarioRepository.save(cuidador);
            usuarioRepository.save(responsavel);
        }
    }
    public void removerInteresse(String interessado, String interessante) {
        Usuario perfilInteressado = usuarioRepository.findByEmail(interessado).get();
        Usuario perfilInteressante = usuarioRepository.findByEmail(interessante).get();

        if (perfilInteressado.getTipo().equals(UsuarioTipo.RESPONSAVEL)) {
            Responsavel responsavel  = (Responsavel) perfilInteressado;
            Cuidador cuidador = (Cuidador) perfilInteressante;

            responsavel.getCuidadores().remove(cuidador);
            cuidador.getResponsaveis().remove(responsavel);

            usuarioRepository.save(cuidador);
            usuarioRepository.save(responsavel);
        }
    }
    public List<UsuarioReturnDTO> relacionados(String email) {
        Usuario user = usuarioRepository.findByEmail(email).get();
        List<UsuarioReturnDTO> resposta = new ArrayList<>();
        if (user.getTipo().equals(UsuarioTipo.CUIDADOR)) {
            Cuidador cuidador = (Cuidador) user;
            List<Responsavel> responsaveis = cuidador.getResponsaveis();
            for (Responsavel r : responsaveis) {
                resposta.add(UsuarioReturnDTO.convert((Usuario) r));
            }
        } else {
            Responsavel responsavel = (Responsavel) user;
            List<Cuidador> cuidadores = responsavel.getCuidadores();
            for (Cuidador c:cuidadores) {
                resposta.add(UsuarioReturnDTO.convert((Usuario) c));
            }
        }
        return resposta;
    }
    public List<UsuarioReturnDTO> usuarios() {
        List<Usuario> us = usuarioRepository.findAll();
        List<UsuarioReturnDTO> usuarios = new ArrayList<UsuarioReturnDTO>();
        for (Usuario u : us) {
            usuarios.add(UsuarioReturnDTO.convert(u));
        }
        return usuarios;
    }
    public UsuarioTipo usuarioTipo(String email) {
        Usuario user = usuarioRepository.findByEmail(email).get();
        return user.getTipo();
    }
    public CuidadorReturnDTO cuidador(String email, String email_view) {
        Cuidador cuidador = (Cuidador) usuarioRepository.findByEmail(email).get();
        boolean rel =  false;
        for (Responsavel r : cuidador.getResponsaveis()) {
            if (r.getEmail().equals(email_view)) {
                rel = true;
            }
        }
        if (cuidador != null) {
            return CuidadorReturnDTO.convert(cuidador,rel);
        }
        return null;
    }
    public List<CuidadorReturnDTO> cuidadores(String visualizador) {
        List<Usuario> cuidadores = usuarioRepository.findByTipo(UsuarioTipo.CUIDADOR);
        List<CuidadorReturnDTO> retorno = new ArrayList<>();
        for (Usuario c : cuidadores) {
            Cuidador C = (Cuidador) c;
            retorno.add(CuidadorReturnDTO.convert(C,false));
        }
        return retorno;
    }
    public List<ResponsavelReturnDTO> responsaveis() {
        List<Usuario> responsaveis = usuarioRepository.findByTipo(UsuarioTipo.RESPONSAVEL);
        List<ResponsavelReturnDTO> retorno = new ArrayList<>();
        for (Usuario r : responsaveis) {
            retorno.add(ResponsavelReturnDTO.convert((Responsavel)r));
        }
        return retorno;
    }
    public ResponsavelReturnDTO responsavel(String email) {
        Responsavel responsavel = (Responsavel) usuarioRepository.findByEmail(email).get();
        if (responsavel != null) {
            return ResponsavelReturnDTO.convert(responsavel);
        }
        return null;
    }
}
