package pvc.caracol.empresarial.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pvc.caracol.empresarial.models.CentroGestion;
import pvc.caracol.empresarial.models.Tienda;

public interface ITiendaRepository extends JpaRepository<Tienda, String> {
}
