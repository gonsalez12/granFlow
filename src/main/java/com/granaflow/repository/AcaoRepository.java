package com.granaflow.repository;

import com.granaflow.model.Acao;
import com.granaflow.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AcaoRepository extends JpaRepository<Acao, String> {
    Optional<Acao> findByCodigo(String codigo);
}
