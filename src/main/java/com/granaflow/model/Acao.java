package com.granaflow.model;

import com.granaflow.domain.CategoriaAcao;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "acao")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Acao {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String nome;

    private String codigo;

    @Enumerated(EnumType.STRING)
    private CategoriaAcao categoria;

    private Double valor;


}
