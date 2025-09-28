package com.granaflow.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "gasto_fixo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GastoFixo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String categoria;

    private String descricao;

    private BigDecimal valor;

    @OneToOne
    @JoinColumn(name = "perfilFinanceiro_id", referencedColumnName = "id", unique = true)
    private PerfilFinanceiro perfilFinanceiro;

}
