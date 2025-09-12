package com.granaflow.dto.usuario;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CadastroRequest {
    @NotBlank
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private String nome;
}
