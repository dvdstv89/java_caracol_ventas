package pvc.caracol.tienda.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Entity
@Getter
@Setter
@Table
public class LogTrabajadorCajero {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "trabajador_cajero_id")
    TrabajadorCajero trabajadorCajero;

    //Fecha de inicio del periodo que ejerce como cajero
    @Column(nullable = false)
    private Date fechaInicio;

    //Fecha en fin del periodo que termina como cajero, si es null segnifica que esta vigente
    private Date fechaFin;

    @Column(nullable = false)
    private String descripcion;

    //en este periodo estaba de vacaciones, franco, baja
    private EstadoCajero estadoCajero;

    //el estado del trabajador se cambia desde un endpoint especifico y se busca en todas las tiendas donde ejerce como cajero
}
