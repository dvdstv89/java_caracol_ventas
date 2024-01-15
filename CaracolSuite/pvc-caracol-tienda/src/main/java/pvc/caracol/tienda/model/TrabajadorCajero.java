package pvc.caracol.tienda.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table
public class TrabajadorCajero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "trabajador_id")
    private Trabajador trabajador;

    @ManyToOne
    @JoinColumn(name = "cajero_id")
    private Cajero cajero;

    @OneToMany(mappedBy = "trabajadorCajero")
    List<LogTrabajadorCajero> logCajeros;
}
