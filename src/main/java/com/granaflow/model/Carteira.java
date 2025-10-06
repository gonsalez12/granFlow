package com.granaflow.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "carteira")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Carteira {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer quantidade;

    private Double precoMedio;

    private Double valorTotalInvestido;

    private Double valorAtual;

    @OneToOne
    @JoinColumn(name = "acao_id", referencedColumnName = "id", unique = true)
    private Acao acao;

    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id", unique = true)
    private Usuario usuario;

}
