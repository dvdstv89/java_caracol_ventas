package pvc.caracol.tienda.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "registro_operacion_cajero")
public class RegistroOperacionesCajero {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "dia_operacion_id")
    private DiaOperacion diaOperacion;

    @ManyToOne
    @JoinColumn(name = "cajero_id")
    private Cajero cajero;

    private boolean haceFuncionSupervisor;
    private int cantidadCorrecciones;
    private int cantidadTVOID;
    private int cantidadRFUND;
    private int cantidadClientes;
    private int cantidadReportes;
    private double cantidadProductos;
    private double cantidadInsumos;

    @OneToMany(mappedBy = "registroOperacionCajero", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VentaCajero> ventaCajeros;
}
