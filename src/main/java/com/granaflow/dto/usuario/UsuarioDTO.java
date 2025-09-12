package com.granaflow.dto.usuario;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UsuarioDTO {
    private String  id;
    private String nome;
    private String email;
    private String role;
}