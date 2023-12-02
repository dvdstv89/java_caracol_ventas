package com.caracol.zunpr.service;

import com.caracol.zunpr.dtos.EmpleadoDto;

import java.util.List;

public interface IEmpleadoService {
    List<EmpleadoDto> getEmpleadosActivos();
}
