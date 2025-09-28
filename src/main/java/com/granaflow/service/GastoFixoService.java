package com.granaflow.service;

import com.granaflow.dto.gastoFIxo.GastoFixoDTO;
import com.granaflow.dto.gastoFIxo.GastoFixoRequest;
import com.granaflow.dto.perfilFinanceiro.PerfilFinanceiroDTO;
import com.granaflow.dto.usuario.UsuarioDTO;
import com.granaflow.exception.BusinessException;
import com.granaflow.model.GastoFixo;
import com.granaflow.model.PerfilFinanceiro;
import com.granaflow.model.Usuario;
import com.granaflow.repository.GastoFixoRepository;
import com.granaflow.repository.PerfilFinanceiroRepository;
import com.granaflow.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GastoFixoService {

    @Autowired
    private GastoFixoRepository gastoFixoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PerfilFinanceiroRepository perfilFinanceiroRepository;

    public GastoFixo criarGastoFixo(String userEmail, GastoFixoRequest request) {

        var usuario = usuarioRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        var perfilFinanceiro = perfilFinanceiroRepository.findByUsuario(usuario)
                .orElseThrow(() -> new RuntimeException("Perfil financeiro não encontrado para o usuário"));

        GastoFixo gastoFixo = GastoFixo.builder()
                .categoria(request.getCategoria())
                .descricao(request.getDescricao())
                .valor(request.getValor())
                .perfilFinanceiro(perfilFinanceiro)
                .build();

        return gastoFixoRepository.save(gastoFixo);
    }

    public GastoFixoDTO listarGastosFixos(String userEmail) {
        Usuario usuario = usuarioRepository.findByEmail(userEmail)
                .orElseThrow(() -> new BusinessException("Usuário não encontrado"));

        PerfilFinanceiro perfilFinanceiro = perfilFinanceiroRepository.findByUsuario(usuario)
                .orElseThrow(() -> new BusinessException("Perfil financeiro não encontrado para o usuário"));

        List<GastoFixo> gastosFixos = gastoFixoRepository.findByPerfilFinanceiro(perfilFinanceiro);


        UsuarioDTO usuarioDTO = UsuarioDTO.builder()
                .email(usuario.getEmail())
                .nome(usuario.getNome())
                .build();

        PerfilFinanceiroDTO perfilFinanceiroDTO = PerfilFinanceiroDTO.builder()
                .rendaMensal(perfilFinanceiro.getRendaMensal())
                .resevaDeEmergencia(perfilFinanceiro.getResevaDeEmergencia())
                .build();

        GastoFixoDTO gastoFixoDTO = GastoFixoDTO.builder()
                .usuario(usuarioDTO)
                .perfilFinanceiro(perfilFinanceiroDTO)
                .gastosFixos(gastosFixos)
                .build();
        return gastoFixoDTO;
    }

    public void deletarGastoFixo(String email, Long gastoFixoId) {
        var usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new BusinessException("Usuário não encontrado"));

        var perfilFinanceiro = perfilFinanceiroRepository.findByUsuario(usuario)
                .orElseThrow(() -> new BusinessException("Perfil financeiro não encontrado para o usuário"));

        List<GastoFixo> gastosFixos = gastoFixoRepository.findByPerfilFinanceiro(perfilFinanceiro);


        if (gastosFixos.stream().noneMatch(g -> g.getId().equals(gastoFixoId))) {
            throw new BusinessException("Gasto fixo não pertence ao perfil financeiro do usuário");
        }


        gastoFixoRepository.deleteById(gastoFixoId);
    }

    public GastoFixo atualizarGastoFixo(String email, Long gastoFixoId, GastoFixoRequest request) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new BusinessException("Usuário não encontrado"));

        PerfilFinanceiro perfilFinanceiro = perfilFinanceiroRepository.findByUsuario(usuario)
                .orElseThrow(() -> new BusinessException("Perfil financeiro não encontrado para o usuário"));


        GastoFixo gastoFixo = gastoFixoRepository.findById(gastoFixoId)
                .orElseThrow(() -> new BusinessException("Gasto fixo não encontrado"));

        List<GastoFixo> gastosFixos = gastoFixoRepository.findByPerfilFinanceiro(perfilFinanceiro);


        if (gastosFixos.stream().noneMatch(g -> g.getId().equals(gastoFixoId))) {
            throw new RuntimeException("Gasto fixo não pertence ao perfil financeiro do usuário");
        }


        gastoFixo.setCategoria(request.getCategoria());
        gastoFixo.setDescricao(request.getDescricao());
        gastoFixo.setValor(request.getValor());
        return gastoFixoRepository.save(gastoFixo);
    }
}
