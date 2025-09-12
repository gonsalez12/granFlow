package com.granaflow.service;

import com.granaflow.dto.perfilFinanceiro.PerfilFinanceiroDTO;
import com.granaflow.dto.perfilFinanceiro.PerfilFinanceiroRequest;
import com.granaflow.dto.usuario.UsuarioDTO;
import com.granaflow.model.PerfilFinanceiro;
import com.granaflow.model.Usuario;
import com.granaflow.repository.PerfilFinanceiroRepository;
import com.granaflow.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PerfilFinanceiroService {

    private final PerfilFinanceiroRepository perfilFinanceiroRepository;
    private final UsuarioRepository usuarioRepository;

    public PerfilFinanceiroDTO criacaoAtualizacao(String usuarioEmail, PerfilFinanceiroRequest request) {

        Usuario usuario = usuarioRepository.findByEmail(usuarioEmail)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        UsuarioDTO usuarioDTO = UsuarioDTO.builder()
                .id(usuario.getId())
                .nome(usuario.getNome())
                .email(usuario.getEmail())
                .build();

        PerfilFinanceiro perfilFinanceiro = perfilFinanceiroRepository.findByUsuario(usuario)
                .orElse(PerfilFinanceiro.builder()
                        .usuario(usuario)
                        .dataAtualizacao(LocalDateTime.now())
                        .build());

        perfilFinanceiro.setRendaMensal(request.getRendaMensal());
        perfilFinanceiro.setResevaDeEmergencia(request.getResevaDeEmergencia());

        PerfilFinanceiro profile = perfilFinanceiroRepository.save(perfilFinanceiro);

        return PerfilFinanceiroDTO.builder()
                .usuario(usuarioDTO)
                .rendaMensal(profile.getRendaMensal())
                .resevaDeEmergencia(profile.getResevaDeEmergencia())
                .build();
    }

    public PerfilFinanceiroDTO buscaProfile(String userEmail) {
        Usuario usuario = usuarioRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        UsuarioDTO usuarioDTO = UsuarioDTO.builder()
                .id(usuario.getId())
                .nome(usuario.getNome())
                .email(usuario.getEmail())
                .build();

        PerfilFinanceiro perfilFinanceiro = perfilFinanceiroRepository.findByUsuario(usuario)
                .orElseThrow(() -> new RuntimeException("Perfil financeiro não encontrado"));

        return PerfilFinanceiroDTO.builder()
                .usuario(usuarioDTO)
                .rendaMensal(perfilFinanceiro.getRendaMensal())
                .resevaDeEmergencia(perfilFinanceiro.getResevaDeEmergencia())
                .build();
    }

}
