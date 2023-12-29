package pvc.caracol.empresarial.models;

import jakarta.persistence.*;
import lombok.Data;
import pvc.caracol.empresarial.enums.TipoMetrocontador;

@Entity
@Data
@Table(name = "metrocontador")
public class Metrocontador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String code;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "tipo_metro")
    private TipoMetrocontador tipoMetrocontador;

    //las relaciones son porque pueden estar en un complejo, o grupo de tienda especifico o en el edificio de oficinas

    @ManyToOne
    @JoinColumn(name = "id_centro_gestion", nullable = true)
    private CentroGestion centroGestion;

    @ManyToOne
    @JoinColumn(name = "id_sucursal_comercial", nullable = true)
    private SucursalComercial sucursalComercial;
}
