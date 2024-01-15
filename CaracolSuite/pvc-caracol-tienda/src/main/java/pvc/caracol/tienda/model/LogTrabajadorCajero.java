package pvc.caracol.tienda.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import pvc.caracol.tienda.enums.TipoLog;

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

    @Column(nullable = false)
    private Date fecha;

    @Column(nullable = false)
    private String descripcion;

    private TipoLog tipoLog;
}
