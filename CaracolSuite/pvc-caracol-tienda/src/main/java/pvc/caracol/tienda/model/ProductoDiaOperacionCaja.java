package pvc.caracol.tienda.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import pvc.caracol.tienda.model.pk.ProductoDiaOperacionCajaId;

@Entity
@Getter
@Setter
@Table(name = "producto_dia_operacion_caja")
@IdClass(ProductoDiaOperacionCajaId.class)
public class ProductoDiaOperacionCaja {

    @Id
    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Producto producto;

    @Id
    @ManyToOne
    @JoinColumn(name = "dia_operacion_id")
    private DiaOperacion diaOperacion;

    @Column(nullable = false)
    private double cantidad;
}
