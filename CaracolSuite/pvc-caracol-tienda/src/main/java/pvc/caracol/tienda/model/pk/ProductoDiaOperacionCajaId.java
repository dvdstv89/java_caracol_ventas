package pvc.caracol.tienda.model.pk;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;


@Getter
@Setter
public class ProductoDiaOperacionCajaId implements Serializable {
    private String producto;
    private Long diaOperacion;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductoDiaOperacionCajaId that = (ProductoDiaOperacionCajaId) o;
        return Objects.equals(producto, that.producto) && Objects.equals(diaOperacion, that.diaOperacion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(producto, diaOperacion);
    }
}
