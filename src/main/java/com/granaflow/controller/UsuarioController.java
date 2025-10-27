package com.granaflow.controller;

import com.granaflow.dto.usuario.AtualizacaoRequest;
import com.granaflow.dto.usuario.CadastroRequest;
import com.granaflow.model.Usuario;
import com.granaflow.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;




@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping("/cadastro")
    public ResponseEntity<String> register(@Valid @RequestBody CadastroRequest request) {

        Usuario usuario = usuarioService.cadastro(request);
        return ResponseEntity.ok("Usaurio registrado com sucesso! " + usuario.getNome());
    }

    @PostMapping("/update")
    public ResponseEntity<String> updateUser(
            @RequestBody AtualizacaoRequest request,
            Authentication authentication) {
        String requesterEmail = authentication.getName();
        Usuario usuarioAtualizado = usuarioService.atualizacao(request, requesterEmail);
        return ResponseEntity.ok("Usuário atualizado com sucesso: " + usuarioAtualizado.getNome());
    }

}
