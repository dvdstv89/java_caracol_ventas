package pvc.caracol.zunpr.service;

import pvc.caracol.zunpr.dtos.EmpleadoDto;

import java.util.List;

public interface IEmpleadoService {
    List<EmpleadoDto> getEmpleadosActivos();
}
