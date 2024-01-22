package pvc.caracol.tienda.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "dia_operacion")
public class DiaOperacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Date fecha;

    @ManyToOne
    @JoinColumn(name = "id_cinta_auditora_id")
    private CintaAuditora cintaAuditora;

    @ManyToOne
    @JoinColumn(name = "caja_registradora_id", nullable = false)
    private CajaRegistradora cajaRegistradora;

    @OneToMany(mappedBy = "diaOperacion", fetch = FetchType.EAGER)
    private List<TransaccionPago> transaccionesPagos;

    @OneToMany(mappedBy = "diaOperacion")
    private List<RegistroOperacionesCajero> registrosOperacionesCajeros;

    @OneToMany(mappedBy = "diaOperacion")
    private List<ProductoDiaOperacionCaja> productos;
}
