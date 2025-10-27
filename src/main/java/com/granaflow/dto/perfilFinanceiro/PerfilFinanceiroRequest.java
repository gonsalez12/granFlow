package com.granaflow.dto.perfilFinanceiro;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NonNull;

import java.math.BigDecimal;

@Data
public class PerfilFinanceiroRequest {


    private BigDecimal rendaMensal;


    private BigDecimal reservaDeEmergencia;
}
