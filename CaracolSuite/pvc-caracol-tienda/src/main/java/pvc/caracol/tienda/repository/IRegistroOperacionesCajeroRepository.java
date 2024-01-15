package pvc.caracol.tienda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pvc.caracol.tienda.model.Cajero;
import pvc.caracol.tienda.model.DiaOperacion;
import pvc.caracol.tienda.model.RegistroOperacionesCajero;

import java.util.Optional;

public interface IRegistroOperacionesCajeroRepository extends JpaRepository<RegistroOperacionesCajero, Long> {
    Optional<RegistroOperacionesCajero> findByDiaOperacionAndCajero(DiaOperacion diaOperacion, Cajero cajero);
}
