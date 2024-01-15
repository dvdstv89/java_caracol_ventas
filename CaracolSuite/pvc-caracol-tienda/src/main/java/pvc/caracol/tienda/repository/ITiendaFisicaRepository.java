package pvc.caracol.tienda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pvc.caracol.tienda.model.TiendaFisica;

public interface ITiendaFisicaRepository extends JpaRepository<TiendaFisica, String> {
}
