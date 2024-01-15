package pvc.caracol.tienda.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "tienda_fisica")
public class TiendaFisica extends Tienda{
    @OneToMany(mappedBy = "tienda", fetch = FetchType.EAGER)
    private List<CajaRegistradora> cajaRegistradoras;
}
