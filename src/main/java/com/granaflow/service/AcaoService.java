package com.granaflow.service;

import com.granaflow.component.BuscaPrecoClient;
import com.granaflow.dto.acao.AcaoRequest;
import com.granaflow.exception.BusinessException;
import com.granaflow.model.Acao;
import com.granaflow.repository.AcaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AcaoService {

    @Autowired
    private AcaoRepository  acaoRepository;

    @Autowired
    private final BuscaPrecoClient buscaPrecoClient;

    public Acao cadastro(AcaoRequest request) {
        if (acaoRepository.findByCodigo(request.getCodigo()).isPresent()) {
            throw new BusinessException("Ação já existe!");
        }

        Acao acao = Acao.builder()
                .codigo(request.getCodigo())
                .nome(request.getNome())
                .categoria(request.getCategoria())
                .build();

        return acaoRepository.save(acao);
    }

    public void atualizarPrecos() {
        var acoes = acaoRepository.findAll();
        for (Acao acao : acoes) {
            Double preco = buscaPrecoClient.getPreco(acao.getCodigo());
            if (preco != null) {
                acao.setValor(preco);
                acaoRepository.save(acao);
            }

        }
    }

}
