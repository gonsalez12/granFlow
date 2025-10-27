package com.granaflow.repository;

import com.granaflow.model.GastoVariavel;
import com.granaflow.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface GastoVariavelRepository extends JpaRepository<GastoVariavel, Long> {
    List<GastoVariavel> findByUsuario(Usuario usuario);
}
