package pvc.caracol.tienda.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "caja_registradora")
public class CajaRegistradora {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String idCaja;

    @Column(nullable = false)
    private String codigoAlmacen;

    @Column(nullable = false)
    private String codigoRed;

    @OneToMany(mappedBy = "cajaRegistradora")
    private List<Cajero> cajeros;

    @OneToMany(mappedBy = "cajaRegistradora")
    private List<DiaOperacion> diaOperacions;

    @OneToMany(mappedBy = "cajaRegistradora")
    private List<CintaAuditora> cintaAuditoras;

    @ManyToOne
    @JoinColumn(name = "tienda_id", nullable = false)
    private TiendaFisica tienda;
}
