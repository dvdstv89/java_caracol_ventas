package pvc.caracol.tienda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pvc.caracol.tienda.model.Producto;

public interface IProductoRepository extends JpaRepository<Producto, String> {

}
