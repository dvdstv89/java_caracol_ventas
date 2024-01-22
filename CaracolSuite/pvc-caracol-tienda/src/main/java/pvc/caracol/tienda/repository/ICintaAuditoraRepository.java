package pvc.caracol.tienda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pvc.caracol.tienda.model.CajaRegistradora;
import pvc.caracol.tienda.model.CintaAuditora;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Optional;

public interface ICintaAuditoraRepository extends JpaRepository<CintaAuditora, Integer> {
    Optional<CintaAuditora> findByFechaCreacionAndFechaHaladoVentaAndCajaRegistradora(Timestamp fechaCreacion, Timestamp fechaHaladoVenta, CajaRegistradora cajaRegistradora);

    @Query("SELECT COUNT(ca) > 0 FROM CintaAuditora ca " +
            "WHERE ca.cajaRegistradora.tienda.code = :idTienda " +
            "AND ca.cajaRegistradora.idCaja = :idCaja " +
            "AND ca.cajaRegistradora.codigoAlmacen = :codigoAlmacen " +
            "AND ca.cajaRegistradora.codigoRed = :codigoRed " +
         //   "AND CAST(ca.fechaCreacion AS DATE) = :fechaCreacion")
            "AND ca.fechaCreacionMistal = :fechaCreacionMistal " +
            "AND ca.fechaProcesadoMistal = :fechaProcesadoMistal ")
    public boolean existsCintaAuditora(@Param("idTienda") String idTienda,
                                       @Param("idCaja") String idCaja,
                                       @Param("codigoAlmacen") String codigoAlmacen,
                                       @Param("codigoRed") String codigoRed,
                                       @Param("fechaCreacionMistal") Date fechaCreacionMistal,
                                       @Param("fechaProcesadoMistal") Date fechaProcesadoMistal);
}
