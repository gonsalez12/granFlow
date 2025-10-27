package com.granaflow.controller;


import com.granaflow.dto.perfilFinanceiro.PerfilFinanceiroResponse;
import com.granaflow.dto.perfilFinanceiro.PerfilFinanceiroRequest;
import com.granaflow.service.PerfilFinanceiroService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;

@RestController
@RequestMapping("/perfil-financeiro")
@RequiredArgsConstructor
public class PerfilFinanceiroController {

    private final PerfilFinanceiroService perfilFinanceiroService;

    @PostMapping
    public ResponseEntity<PerfilFinanceiroResponse> criacaoAtualizacao(@RequestBody PerfilFinanceiroRequest request,
                                                                       Authentication authentication) {
        String userEmail = authentication.getName();

        return ResponseEntity.ok(perfilFinanceiroService.criacaoAtualizacao(userEmail, request));
    }

    @GetMapping
    public ResponseEntity<PerfilFinanceiroResponse> buscarPerfilFinanceiro(Authentication authentication) {
        String userEmail = authentication.getName();

        PerfilFinanceiroResponse perfilFinanceiroResponse = perfilFinanceiroService.buscarPorUsuarioEmail(userEmail);
        return ResponseEntity.ok(perfilFinanceiroResponse);
    }

}

