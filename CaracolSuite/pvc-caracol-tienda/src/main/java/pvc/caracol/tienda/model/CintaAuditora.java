package pvc.caracol.tienda.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "cinta_auditora")
public class CintaAuditora {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "paper_size_cm")
    private double paperSize;
    private int countLines;
    private long byteSize;

    @OneToMany(mappedBy = "cintaAuditora")
    private List<DiaOperacion> diaOperacions;

    @ManyToOne
    @JoinColumn(name = "caja_registradora_id", nullable = false)
    private CajaRegistradora cajaRegistradora;

    private Timestamp fechaCreacion;
    private Timestamp fechaHaladoVenta;
    private Timestamp fechaAnalisis;
}
