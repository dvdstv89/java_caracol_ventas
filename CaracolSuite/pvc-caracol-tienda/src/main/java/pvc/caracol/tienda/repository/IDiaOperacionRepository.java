package pvc.caracol.tienda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pvc.caracol.tienda.model.CajaRegistradora;
import pvc.caracol.tienda.model.DiaOperacion;

import java.time.LocalDate;
import java.util.Optional;

public interface IDiaOperacionRepository extends JpaRepository<DiaOperacion, Integer> {
    Optional<DiaOperacion> findByFechaAndCajaRegistradora(LocalDate fecha, CajaRegistradora cajaRegistradora);
}
