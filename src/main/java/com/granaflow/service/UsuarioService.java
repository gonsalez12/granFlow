package com.granaflow.service;

import com.granaflow.dto.usuario.AtualizacaoRequest;
import com.granaflow.dto.usuario.CadastroRequest;
import com.granaflow.dto.usuario.UsuarioResponse;
import com.granaflow.exception.BusinessException;
import com.granaflow.model.Usuario;
import com.granaflow.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public Usuario cadastro(CadastroRequest request) {
        if (usuarioRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new BusinessException("Usuário já existe!");
        }

        Usuario usuario = Usuario.builder()
                .email(request.getEmail())
                .passwordHash(passwordEncoder.encode(request.getPassword()))
                .nome(request.getNome())
                .role("ROLE_USER")
                .build();

        return usuarioRepository.save(usuario);
    }

    public Usuario atualizacao(AtualizacaoRequest request, String requesterEmail) {

        Usuario usuarioAtualiza = usuarioRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new BusinessException("Usuário não encontrado para atualização"));

        Usuario solicitante = usuarioRepository.findByEmail(requesterEmail)
                .orElseThrow(() -> new BusinessException("Usuário solicitante não encontrado"));

        if (!solicitante.getEmail().equals(usuarioAtualiza.getEmail())
                && !solicitante.getRole().equals("ROLE_ADMIN")) {
            throw new BusinessException("Apenas administradores podem atualizar outros usuários");
        }

        usuarioAtualiza.setNome(request.getNome());
        usuarioAtualiza.setPasswordHash(passwordEncoder.encode(request.getPassword()));



        return usuarioRepository.save(usuarioAtualiza);
    }



}
