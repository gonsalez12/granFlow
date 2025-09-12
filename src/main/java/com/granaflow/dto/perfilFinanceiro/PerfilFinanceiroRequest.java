package com.granaflow.dto.perfilFinanceiro;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PerfilFinanceiroRequest {

    @JsonProperty("renda_mensal")
    private BigDecimal rendaMensal;

    @JsonProperty("reserva_emergencia")
    private BigDecimal resevaDeEmergencia;
}
