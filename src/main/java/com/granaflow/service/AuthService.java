package com.granaflow.service;

import com.granaflow.config.JwtUtil;
import com.granaflow.dto.*;
import com.granaflow.model.User;
import com.granaflow.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;


    public User register(RegisterRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Usuário já existe!");
        }

        User user = User.builder()
                .email(request.getEmail())
                .passwordHash(passwordEncoder.encode(request.getPassword()))
                .name(request.getName())
                .role("ROLE_USER")
                .build();

        return userRepository.save(user);
    }

    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPasswordHash())) {
            throw new RuntimeException("Senha inválida");
        }

        String token = jwtUtil.generateToken(user.getEmail());
        return new LoginResponse(token);
    }

    public User updateUser(UpdateRequest request, String requesterEmail) {

        User userToUpdate = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado para atualização"));

        User requester = userRepository.findByEmail(requesterEmail)
                .orElseThrow(() -> new RuntimeException("Usuário solicitante não encontrado"));

        if (!requester.getEmail().equals(userToUpdate.getEmail())
                && !requester.getRole().equals("ROLE_ADMIN")) {
            throw new RuntimeException("Apenas administradores podem atualizar outros usuários");
        }

        userToUpdate.setName(request.getName());
        userToUpdate.setPasswordHash(request.getPassword());



        return userRepository.save(userToUpdate);
    }

    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(user -> new UserDTO(
                        user.getId(),
                        user.getName(),
                        user.getEmail(),
                        user.getRole())
                )
                .toList();
    }

}
