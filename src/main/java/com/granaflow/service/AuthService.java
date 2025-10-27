package com.granaflow.service;

import com.granaflow.config.JwtUtil;
import com.granaflow.dto.login.LoginRequest;
import com.granaflow.dto.login.LoginResponse;
import com.granaflow.dto.perfilFinanceiro.PerfilFinanceiroResponse;
import com.granaflow.dto.usuario.UsuarioResponse;
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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PerfilFinanceiroRepository perfilFinanceiroRepository;
    private final GastoFixoRepository gastoFixoRepository;
    private final GastoVariavelRepository gastoVariavelRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;


    public LoginResponse login(LoginRequest request) {
        Usuario usuario = usuarioRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new BusinessException("Usuário não encontrado"));

        if (!passwordEncoder.matches(request.getPassword(), usuario.getPasswordHash())) {
            throw new BusinessException("Senha inválida");
        }

        String token = jwtUtil.generateToken(usuario.getEmail());

        PerfilFinanceiro perfilFinanceiro = perfilFinanceiroRepository.findByUsuario(usuario)
                .orElse(null);

        if(perfilFinanceiro != null) {
            List<GastoFixo> gastosFixos = gastoFixoRepository.findByUsuario(usuario);

            BigDecimal totalGastosFixos = gastosFixos.stream()
                    .map(GastoFixo::getValorMensal)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            List<GastoVariavel> gastoVariavels = gastoVariavelRepository.findByUsuario(usuario);

            BigDecimal totalGastosVariaveis = gastoVariavels.stream()
                    .map(GastoVariavel::getValor)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
            PerfilFinanceiroResponse perfilFinanceiroResponse = PerfilFinanceiroResponse.fromEntity(perfilFinanceiro,
                    totalGastosFixos,
                    totalGastosVariaveis);

            return LoginResponse.builder()
                    .token(token)
                    .usuarioResponse(UsuarioResponse.fromEntity(usuario))
                    .perfilFinanceiroResponse(perfilFinanceiroResponse)
                    .build();
        }

        return LoginResponse.builder()
                .token(token)
                .usuarioResponse(UsuarioResponse.fromEntity(usuario))
                .build();


    }
}