package com.granaflow.repository;


import com.granaflow.model.PerfilFinanceiro;
import com.granaflow.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PerfilFinanceiroRepository extends JpaRepository<PerfilFinanceiro, Long> {
    Optional<PerfilFinanceiro> findByUsuario(Usuario usuario);
}
