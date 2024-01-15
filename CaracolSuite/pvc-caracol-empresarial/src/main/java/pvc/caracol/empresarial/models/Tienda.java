package pvc.caracol.empresarial.models;

import jakarta.persistence.*;
import lombok.Data;
import pvc.caracol.empresarial.enums.TipoTienda;

@Entity
@Data
@Table(name = "tienda")
public class Tienda {
    @Id
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

    @Column(nullable = false)
    private String centroCosto;

    private Integer ordenComercial;

    private boolean isMlc;

    private boolean abierta;

    private boolean active;
}
