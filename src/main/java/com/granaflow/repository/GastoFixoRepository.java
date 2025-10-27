package com.granaflow.repository;

import com.granaflow.model.GastoFixo;
import com.granaflow.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface GastoFixoRepository extends JpaRepository<GastoFixo, Long> {
    List<GastoFixo> findByUsuario(Usuario usuario);
}
