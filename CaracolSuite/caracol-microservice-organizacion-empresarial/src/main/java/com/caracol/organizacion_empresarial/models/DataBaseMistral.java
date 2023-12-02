package com.caracol.organizacion_empresarial.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "database_mistral")
public class DataBaseMistral {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String code;

    @Column(nullable = false)
    private String host;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer  port;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column
    private String description;
}
