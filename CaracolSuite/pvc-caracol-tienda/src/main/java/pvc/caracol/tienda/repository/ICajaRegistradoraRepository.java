package pvc.caracol.tienda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pvc.caracol.tienda.model.CajaRegistradora;
import pvc.caracol.tienda.model.TiendaFisica;

import java.util.Optional;

public interface ICajaRegistradoraRepository extends JpaRepository<CajaRegistradora, Integer> {
    Optional<CajaRegistradora> findByIdCajaAndCodigoAlmacenAndCodigoRedAndTienda(String idCaja, String codigoAlmacen, String codigoRed, TiendaFisica tienda);
}
