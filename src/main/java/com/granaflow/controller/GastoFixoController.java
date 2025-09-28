package com.granaflow.controller;

import com.granaflow.dto.gastoFIxo.GastoFixoDTO;
import com.granaflow.dto.gastoFIxo.GastoFixoRequest;
import com.granaflow.model.GastoFixo;
import com.granaflow.service.GastoFixoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;

@RestController
@RequestMapping("/gastos-fixos")
@RequiredArgsConstructor
public class GastoFixoController {

    @Autowired
    private GastoFixoService gastoFixoService;


    @PostMapping
    public ResponseEntity<GastoFixo> criacaoAtualizacao(@RequestBody GastoFixoRequest request,
                                                        Authentication authentication) {
        String userEmail = authentication.getName();
        return ResponseEntity.ok(gastoFixoService.criarGastoFixo(userEmail, request));
    }

    @GetMapping
    public ResponseEntity<GastoFixoDTO> listarGastosFixos(Authentication authentication) {
        String userEmail = authentication.getName();
        return ResponseEntity.ok(gastoFixoService.listarGastosFixos(userEmail));
    }

    @DeleteMapping("/{gastoFixoId}")
    public ResponseEntity<Void> deletarGastoFixo(@PathVariable Long gastoFixoId,
                                                 Authentication authentication) {
        String email = authentication.getName();
        gastoFixoService.deletarGastoFixo(email, gastoFixoId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{gastoFixoId}")
    public ResponseEntity<GastoFixo> atualizarGastoFixo(@PathVariable Long gastoFixoId,
                                                        @RequestBody GastoFixoRequest request,
                                                        Authentication authentication) {
        String email = authentication.getName();
        return ResponseEntity.ok(gastoFixoService.atualizarGastoFixo(email, gastoFixoId, request));
    }

}
