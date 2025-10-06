package com.granaflow.dto.acao;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.granaflow.domain.CategoriaAcao;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AcaoRequest {

    @NotNull
    private String codigo;

    @NotNull
    private String nome;

    @NotNull
    @JsonProperty("categoria_acao")
    private CategoriaAcao categoria;

}
