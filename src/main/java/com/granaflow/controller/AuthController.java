package com.granaflow.controller;

import com.granaflow.dto.LoginRequest;
import com.granaflow.dto.LoginResponse;
import com.granaflow.dto.RegisterRequest;
import com.granaflow.dto.UpdateRequest;
import com.granaflow.model.User;
import com.granaflow.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;


    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterRequest request) {
        User user = authService.register(request);
        return ResponseEntity.ok("Usaurio registrado com sucesso!" + user.getName());
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/update")
    public ResponseEntity<String> updateUser(
            @RequestBody UpdateRequest request,
            Authentication authentication) {
        String requesterEmail = authentication.getName();
        User updatedUser = authService.updateUser(request, requesterEmail);
        return ResponseEntity.ok("Usuário atualizado com sucesso: " + updatedUser.getName());
    }
}
