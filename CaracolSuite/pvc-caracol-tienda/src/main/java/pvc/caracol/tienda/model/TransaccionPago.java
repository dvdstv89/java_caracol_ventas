package pvc.caracol.tienda.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import pvc.caracol.common.enums.FormaPago;

@Entity
@Getter
@Setter
@Table(name = "transaccion_pago")
public class TransaccionPago {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "dia_operacion_id", nullable = false)
    DiaOperacion diaOperacion;

    @Enumerated(EnumType.STRING)
    @Column(name = "forma_pago", nullable = false)
    FormaPago formaPago;

    private Boolean propina;

    private Double saldo;
}
