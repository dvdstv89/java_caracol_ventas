package pvc.caracol.empresarial.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pvc.caracol.empresarial.models.CentroGestion;

public interface ICentroGestionRepository extends JpaRepository<CentroGestion, Integer> {
}
