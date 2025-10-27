package com.granaflow.dto.perfilFinanceiro;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.granaflow.model.PerfilFinanceiro;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Builder
@Data
@AllArgsConstructor
public class PerfilFinanceiroResponse {

    @JsonProperty("renda_mensal")
    private BigDecimal rendaMensal;

    @JsonProperty("reserva_emergencia")
    private BigDecimal resevaDeEmergencia;

    @JsonProperty("gastos_fixos")
    private BigDecimal gastosFixos;

    @JsonProperty("gastos_variaveis")
    private BigDecimal gastosVariaveis;

    public static PerfilFinanceiroResponse fromEntity(PerfilFinanceiro perfilFinanceiro,
                                                      BigDecimal gastosFixos,
                                                      BigDecimal gastosVariaveis) {
        return PerfilFinanceiroResponse.builder()
                .rendaMensal(perfilFinanceiro.getRendaMensal())
                .resevaDeEmergencia(perfilFinanceiro.getResevaDeEmergencia())
                .gastosFixos(gastosFixos)
                .gastosVariaveis(gastosVariaveis)
                .build();
    }
}
