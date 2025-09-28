package com.granaflow.dto.gastoFIxo;

import com.granaflow.dto.perfilFinanceiro.PerfilFinanceiroDTO;
import com.granaflow.dto.usuario.UsuarioDTO;
import com.granaflow.model.GastoFixo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
public class GastoFixoDTO {

    private UsuarioDTO usuario;
    private PerfilFinanceiroDTO perfilFinanceiro;
    private List<GastoFixo> gastosFixos;
}
