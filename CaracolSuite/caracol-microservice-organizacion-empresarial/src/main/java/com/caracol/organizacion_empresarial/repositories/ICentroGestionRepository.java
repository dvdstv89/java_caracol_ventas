package com.caracol.organizacion_empresarial.repositories;

import com.caracol.organizacion_empresarial.models.CentroGestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICentroGestionRepository extends JpaRepository<CentroGestion, Integer> {
    CentroGestion findByCode(String code);
}
