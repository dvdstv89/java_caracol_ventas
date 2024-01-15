package pvc.caracol.tienda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pvc.caracol.tienda.model.CajaRegistradora;
import pvc.caracol.tienda.model.CintaAuditora;
import pvc.caracol.tienda.model.TiendaFisica;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Optional;

public interface ICintaAuditoraRepository extends JpaRepository<CintaAuditora, Integer> {
    Optional<CintaAuditora> findByFechaCreacionAndFechaHaladoVentaAndCajaRegistradora(Timestamp fechaCreacion, Timestamp fechaHaladoVenta, CajaRegistradora cajaRegistradora);
}
