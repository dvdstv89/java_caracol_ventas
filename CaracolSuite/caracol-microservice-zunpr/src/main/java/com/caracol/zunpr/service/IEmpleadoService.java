package com.caracol.zunpr.service;

import com.caracol.zunpr.dtos.EmpleadoDTO;
import com.caracol.zunpr.model.Empleado;

import java.util.List;

public interface IEmpleadoService {
    List<EmpleadoDTO> getEmpleadosActivos();
}
