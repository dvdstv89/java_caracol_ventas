package pvc.caracol.cinad.models;

import lombok.Data;
import pvc.caracol.cinad.analizador.sintactico.operaciones.enums.PalabrasReservadasFormasPago;
import pvc.caracol.common.enums.FormaPago;

@Data
public class Pago implements Cloneable {
    private double pagado;
    private FormaPago formaPago;

    public Pago(String formaPago, String monto) {
        setFormaPago(formaPago);
        setMonto(monto);
    }

    private void setFormaPago(String word) {
        formaPago = PalabrasReservadasFormasPago.getFormaPago(word);
    }

    private void setMonto(String monto) {
        try {
            this.pagado = Math.round(Double.parseDouble(monto) * 100.0) / 100.0;
        } catch (Exception ex) {
            this.pagado = 0.0;
        }
    }

    public void addMonto(double monto) {
        double pago = this.pagado + monto;
        this.pagado = Math.round(pago * 100.0) / 100.0;
    }

    @Override
    public Pago clone() {
        try {
            return (Pago) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}
