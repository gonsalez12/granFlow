package com.granaflow.model;

import com.granaflow.domain.CategoriaGasto;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "gastos_variaveis")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GastoVariavel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;

    private BigDecimal valor;

    private LocalDate data; // Data do gasto real

    @Enumerated(EnumType.STRING)
    private CategoriaGasto categoria;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;
}
