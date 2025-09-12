package com.granaflow.model;

import jakarta.persistence.*;
import lombok.*;
import jakarta.persistence.Id;


import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "perfil_financeiro")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PerfilFinanceiro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal rendaMensal;

    private BigDecimal resevaDeEmergencia;

    private LocalDateTime dataAtualizacao;

    @OneToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id", unique = true)
    private Usuario usuario;
}
