package com.system.zunpr.repository;

import com.system.zunpr.model.Empleado;

import java.util.List;

public interface IEmpleadoRepository {
    List<Empleado> getEmpleadosActivos();
}
