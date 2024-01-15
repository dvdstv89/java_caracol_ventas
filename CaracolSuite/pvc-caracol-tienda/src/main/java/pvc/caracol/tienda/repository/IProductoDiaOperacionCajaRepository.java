package pvc.caracol.tienda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pvc.caracol.tienda.model.ProductoDiaOperacionCaja;
import pvc.caracol.tienda.model.pk.ProductoDiaOperacionCajaId;

public interface IProductoDiaOperacionCajaRepository extends JpaRepository<ProductoDiaOperacionCaja, ProductoDiaOperacionCajaId> {

}
