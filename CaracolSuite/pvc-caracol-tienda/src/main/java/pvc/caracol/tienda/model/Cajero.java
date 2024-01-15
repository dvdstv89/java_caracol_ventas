package pvc.caracol.tienda.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table
public class Cajero {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 3)
    private String codigoCajero;

    @ManyToOne
    @JoinColumn(name = "caja_registradora_id", nullable = false)
    private CajaRegistradora cajaRegistradora;

    @OneToMany(mappedBy = "cajero")
    List<RegistroOperacionesCajero> registroOperacionesCajeros;
}
