package pvc.caracol.tienda.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table
public class Trabajador {
    @Id
    @Column(unique = true, nullable = false, length = 7)
    private String codigo;

    @OneToMany(mappedBy = "trabajador")
    List<TrabajadorCajero> trabajadorCajeros;
}
