package pvc.caracol.tienda.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import pvc.caracol.common.enums.FormaPago;

@Entity
@Getter
@Setter
@Table(name = "venta_cajero")
public class VentaCajero {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "forma_pago", nullable = false)
    FormaPago formaPago;

    @ManyToOne
    @JoinColumn(name = "registro_operacion_cajero")
    private RegistroOperacionesCajero registroOperacionCajero;

    private Boolean propina;

    private Double saldo;
}
