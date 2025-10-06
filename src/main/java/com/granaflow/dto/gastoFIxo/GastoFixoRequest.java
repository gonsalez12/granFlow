package com.granaflow.dto.gastoFIxo;


import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GastoFixoRequest {


    private String categoria;
    private String descricao;
    private BigDecimal valor;

}
