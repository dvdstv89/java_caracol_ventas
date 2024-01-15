package pvc.caracol.tienda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pvc.caracol.common.enums.FormaPago;
import pvc.caracol.tienda.model.CajaRegistradora;
import pvc.caracol.tienda.model.DiaOperacion;
import pvc.caracol.tienda.model.TransaccionPago;

import java.time.LocalDate;
import java.util.Optional;

public interface ITransaccionPagoRepository extends JpaRepository<TransaccionPago, Long> {
    Optional<TransaccionPago> findByFormaPagoAndDiaOperacionAndPropina(FormaPago formaPago, DiaOperacion diaOperacion,Boolean propina);
}
