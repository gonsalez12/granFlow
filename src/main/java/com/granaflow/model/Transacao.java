package com.granaflow.model;


import com.granaflow.domain.TipoTransacao;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "transacao")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transacao {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer quantidade;

    private Double valor;

    private Double valorTotal;

    private Date data;

    @Enumerated(EnumType.STRING)
    private TipoTransacao tipoTransacao;

    @OneToOne
    @JoinColumn(name = "acao_id", referencedColumnName = "id", unique = true)
    private Acao acao;

}
