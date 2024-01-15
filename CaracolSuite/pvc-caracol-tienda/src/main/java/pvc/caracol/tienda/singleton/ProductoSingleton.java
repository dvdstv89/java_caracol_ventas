package pvc.caracol.tienda.singleton;

import org.springframework.stereotype.Component;
import pvc.caracol.tienda.model.Producto;

@Component
public class ProductoSingleton extends SingletonList<Producto> {

}
