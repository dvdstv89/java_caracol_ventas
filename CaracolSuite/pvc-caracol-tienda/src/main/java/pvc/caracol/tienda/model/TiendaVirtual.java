package pvc.caracol.tienda.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "tienda_virtual")
public class TiendaVirtual extends Tienda {
}
