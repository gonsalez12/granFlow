package com.granaflow.controller;


import com.granaflow.dto.perfilFinanceiro.PerfilFinanceiroDTO;
import com.granaflow.dto.perfilFinanceiro.PerfilFinanceiroRequest;
import com.granaflow.service.PerfilFinanceiroService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;

@RestController
@RequestMapping("/perfil-financeiro")
@RequiredArgsConstructor
public class FinancialProfileController {

    private final PerfilFinanceiroService financialProfileService;

    @PostMapping
    public ResponseEntity<PerfilFinanceiroDTO> criacaoAtualizacao(@RequestBody PerfilFinanceiroRequest request,
                                                                  Authentication authentication) {
        String userEmail = authentication.getName();

        return ResponseEntity.ok(financialProfileService.criacaoAtualizacao(userEmail, request));
    }

    @GetMapping
    public ResponseEntity<PerfilFinanceiroDTO> buscaPerfil(Authentication authentication) {

        String usuarioEmail = authentication.getName();

        return ResponseEntity.ok(financialProfileService.buscaProfile(usuarioEmail));
    }
}
