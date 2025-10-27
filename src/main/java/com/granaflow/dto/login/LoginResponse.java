package com.granaflow.dto.login;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.granaflow.dto.perfilFinanceiro.PerfilFinanceiroResponse;
import com.granaflow.dto.usuario.UsuarioResponse;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginResponse {
    private String token;

    @JsonProperty("usuario")
    private UsuarioResponse usuarioResponse;

    @JsonProperty("perfilFinanceiro")
    private PerfilFinanceiroResponse perfilFinanceiroResponse;

}
