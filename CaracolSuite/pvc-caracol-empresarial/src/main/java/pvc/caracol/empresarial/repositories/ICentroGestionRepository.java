package pvc.caracol.empresarial.repositories;

import pvc.caracol.empresarial.models.CentroGestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICentroGestionRepository extends JpaRepository<CentroGestion, Integer> {
    CentroGestion findByCode(String code);
}
