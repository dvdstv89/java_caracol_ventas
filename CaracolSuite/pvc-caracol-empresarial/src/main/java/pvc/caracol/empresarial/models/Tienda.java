package pvc.caracol.empresarial.models;

import jakarta.persistence.*;
import lombok.Data;
import pvc.caracol.empresarial.enums.TipoTienda;

@Entity
@Data
@Table(name = "tienda")
public class Tienda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String code;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "tipo_tienda")
    private TipoTienda tipoTienda;

    @ManyToOne
    @JoinColumn(name = "id_centro_gestion", nullable = false)
    private CentroGestion centroGestion;
}
