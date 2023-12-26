package pvc.caracol.empresarial.models;

import pvc.caracol.empresarial.enums.TipoComplejo;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "complejo")
public class Complejo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String code;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "id_sucursal_comercial", nullable = false)
    private SucursalComercial sucursalComercial;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "tipo_complejo")
    private TipoComplejo tipoComplejo;

    @OneToMany(mappedBy = "complejo")
    private List<CentroGestion> centrosGestion;
}
