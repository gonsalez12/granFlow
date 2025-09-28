package com.granaflow.service;

import com.granaflow.config.JwtUtil;
import com.granaflow.dto.login.LoginRequest;
import com.granaflow.dto.login.LoginResponse;
import com.granaflow.exception.BusinessException;
import com.granaflow.model.Usuario;
import com.granaflow.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;



    public LoginResponse login(LoginRequest request) {
        Usuario Usuario = usuarioRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new BusinessException("Usuário não encontrado"));

        if (!passwordEncoder.matches(request.getPassword(), Usuario.getPasswordHash())) {
            throw new BusinessException("Senha inválida");
        }

        String token = jwtUtil.generateToken(Usuario.getEmail());
        return new LoginResponse(token);
    }



}
