package com.caracol.zunpr.repository;

import com.caracol.zunpr.model.Empleado;

import java.util.List;

public interface IEmpleadoRepository {
    List<Empleado> getEmpleadosActivos();
}
