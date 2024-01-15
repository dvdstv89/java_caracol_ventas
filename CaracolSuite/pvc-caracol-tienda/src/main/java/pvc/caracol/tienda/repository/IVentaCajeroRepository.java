package pvc.caracol.tienda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pvc.caracol.common.enums.FormaPago;
import pvc.caracol.tienda.model.RegistroOperacionesCajero;
import pvc.caracol.tienda.model.VentaCajero;

import java.util.Optional;

public interface IVentaCajeroRepository extends JpaRepository<VentaCajero, Long> {
    Optional<VentaCajero> findByFormaPagoAndRegistroOperacionCajeroAndPropina(FormaPago formaPago, RegistroOperacionesCajero registroOperacionCajero, Boolean propina);
}
