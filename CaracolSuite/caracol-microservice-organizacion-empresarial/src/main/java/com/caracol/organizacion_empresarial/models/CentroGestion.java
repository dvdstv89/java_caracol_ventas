package com.caracol.organizacion_empresarial.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "centro_gestion")
public class CentroGestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String code;

    @Column
    private String name;

    @Column
    private String description;

    @ManyToOne
    @JoinColumn(name = "id_complejo", nullable = false)
    private Complejo complejo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_database", referencedColumnName = "id")
    private DataBaseMistral database;
}
