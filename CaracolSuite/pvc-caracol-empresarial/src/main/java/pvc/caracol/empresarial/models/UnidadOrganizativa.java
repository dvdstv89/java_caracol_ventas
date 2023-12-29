package pvc.caracol.empresarial.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "unidad_organizativa")
public class UnidadOrganizativa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "code")
    private Integer codigoUnidadOrganizativa;

    private String description;

    @ManyToOne
    @JoinColumn(name = "id_centro_gestion", nullable = false)
    private CentroGestion centroGestion;


    //bolsa de donde sacar los trabajadores a su disposicion
}
