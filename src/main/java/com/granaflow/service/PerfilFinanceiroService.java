package com.granaflow.service;

import com.granaflow.dto.perfilFinanceiro.PerfilFinanceiroResponse;
import com.granaflow.dto.perfilFinanceiro.PerfilFinanceiroRequest;
import com.granaflow.exception.BusinessException;
import com.granaflow.model.GastoFixo;
import com.granaflow.model.GastoVariavel;
import com.granaflow.model.PerfilFinanceiro;
import com.granaflow.model.Usuario;
import com.granaflow.repository.GastoFixoRepository;
import com.granaflow.repository.GastoVariavelRepository;
import com.granaflow.repository.PerfilFinanceiroRepository;
import com.granaflow.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PerfilFinanceiroService {

    private final PerfilFinanceiroRepository perfilFinanceiroRepository;
    private final UsuarioRepository usuarioRepository;
    private final GastoFixoRepository gastoFixoRepository;
    private final GastoVariavelRepository gastoVariavelRepository;

    public PerfilFinanceiroResponse criacaoAtualizacao(String usuarioEmail, PerfilFinanceiroRequest request) {

        Usuario usuario = usuarioRepository.findByEmail(usuarioEmail)
                .orElseThrow(() -> new BusinessException("Usuário não encontrado"));

        PerfilFinanceiro perfilFinanceiro = perfilFinanceiroRepository.findByUsuario(usuario)
                .orElse(PerfilFinanceiro.builder()
                        .usuario(usuario)
                        .dataAtualizacao(LocalDateTime.now())
                        .build());

        perfilFinanceiro.setRendaMensal(request.getRendaMensal());
        perfilFinanceiro.setResevaDeEmergencia(request.getReservaDeEmergencia());

        PerfilFinanceiro profile = perfilFinanceiroRepository.save(perfilFinanceiro);

        return PerfilFinanceiroResponse.fromEntity(profile, BigDecimal.ZERO, BigDecimal.ZERO);
    }

    public PerfilFinanceiroResponse buscarPorUsuarioEmail(String userEmail) {
        Usuario usuario = usuarioRepository.findByEmail(userEmail)
                .orElseThrow(() -> new BusinessException("Usuário não encontrado"));

        PerfilFinanceiro perfilFinanceiro = perfilFinanceiroRepository.findByUsuario(usuario)
                .orElseThrow(() -> new BusinessException("Perfil financeiro não encontrado"));

        List<GastoFixo> gastosFixos = gastoFixoRepository.findByUsuario(usuario);

        BigDecimal totalGastosFixos = gastosFixos.stream()
                .map(GastoFixo::getValorMensal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        List<GastoVariavel> gastoVariavels = gastoVariavelRepository.findByUsuario(usuario);

        BigDecimal totalGastosVariaveis = gastoVariavels.stream()
                .map(GastoVariavel::getValor)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return PerfilFinanceiroResponse.fromEntity(perfilFinanceiro,
                totalGastosFixos,
                totalGastosVariaveis);
    }

}
