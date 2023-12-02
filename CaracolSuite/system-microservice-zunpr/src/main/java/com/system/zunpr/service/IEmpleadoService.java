package com.system.zunpr.service;

import com.system.zunpr.dtos.EmpleadoDto;

import java.util.List;

public interface IEmpleadoService {
    List<EmpleadoDto> getEmpleadosActivos();
}
