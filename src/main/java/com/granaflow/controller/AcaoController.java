package com.granaflow.controller;

import com.granaflow.dto.acao.AcaoRequest;
import com.granaflow.dto.usuario.CadastroRequest;
import com.granaflow.model.Acao;
import com.granaflow.model.Usuario;
import com.granaflow.service.AcaoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/acao")
@RequiredArgsConstructor
public class AcaoController {


    private final AcaoService acaoService;

    @PostMapping("/cadastro")
    public ResponseEntity<String> register(@Valid @RequestBody AcaoRequest request) {

        Acao acao = acaoService.cadastro(request);
        return ResponseEntity.ok("Ação registrado com sucesso! " + acao.getCodigo());
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateAcao() {
        acaoService.atualizarPrecos();
        return ResponseEntity.ok("Ações atualizado com sucesso");
    }

}
