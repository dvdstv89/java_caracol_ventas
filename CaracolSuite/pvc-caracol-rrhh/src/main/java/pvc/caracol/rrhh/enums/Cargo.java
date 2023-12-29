package pvc.caracol.rrhh.enums;

public enum Cargo {
    DependienteComercial("151", "Dependiente Comercial para el Turismo"),
    JefeBrigada("150", "Jefe Brigada"),
    JefeDepartamento("148", " Jefe de Departamento"),
    AdministradorTiendas("103", "Administrador de Tiendas");

    private String codigoCargo;
    private String denominacion;

    Cargo(String codigoCargo, String denominacion) {
        this.codigoCargo = codigoCargo;
        this.denominacion = denominacion;
    }
}
