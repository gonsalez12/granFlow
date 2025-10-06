package com.granaflow.dto.perfilFinanceiro;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.granaflow.dto.usuario.UsuarioDTO;
import com.granaflow.model.Usuario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Builder
@Data
@AllArgsConstructor
public class PerfilFinanceiroDTO {

    private UsuarioDTO usuario;

    @JsonProperty("renda_mensal")
    private BigDecimal rendaMensal;

    @JsonProperty("reserva_emergencia")
    private BigDecimal resevaDeEmergencia;
}
