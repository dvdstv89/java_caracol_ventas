package pvc.caracol.tienda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pvc.caracol.common.enums.FormaPago;
import pvc.caracol.tienda.model.CajaRegistradora;
import pvc.caracol.tienda.model.Cajero;
import pvc.caracol.tienda.model.DiaOperacion;
import pvc.caracol.tienda.model.TransaccionPago;

import java.util.Optional;

public interface ICajeroRepository extends JpaRepository<Cajero, Long> {
    Optional<Cajero> findByCodigoCajeroAndCajaRegistradora(String codigoCajero, CajaRegistradora cajaRegistradora);
}
