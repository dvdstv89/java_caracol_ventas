package pvc.caracol.zunpr.repository;

import pvc.caracol.zunpr.model.Empleado;

import java.util.List;

public interface IEmpleadoRepository {
    List<Empleado> getEmpleadosActivos();
}
