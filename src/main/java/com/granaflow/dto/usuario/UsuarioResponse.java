package com.granaflow.dto.usuario;

import com.granaflow.model.Usuario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UsuarioResponse {
    private String  id;
    private String nome;
    private String email;
    private String role;

    public static UsuarioResponse fromEntity(Usuario usuario) {
        return UsuarioResponse.builder()
                .id(usuario.getId())
                .nome(usuario.getNome())
                .email(usuario.getEmail())
                .role(usuario.getRole())
                .build();
    }
}