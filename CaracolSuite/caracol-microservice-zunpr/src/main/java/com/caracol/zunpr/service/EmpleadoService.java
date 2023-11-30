package com.caracol.zunpr.service;

import com.caracol.zunpr.dtos.EmpleadoDTO;
import com.caracol.zunpr.model.Empleado;
import com.caracol.zunpr.repository.IEmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmpleadoService implements IEmpleadoService {
    private final IEmpleadoRepository repository;
    private final ModelMapper modelMapper;

    @Autowired
    public EmpleadoService(IEmpleadoRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<EmpleadoDTO> getEmpleadosActivos() {
        List<Empleado> empleados = repository.getEmpleadosActivos();
        List<EmpleadoDTO> empleadoDTOList = empleados.stream()
                .map(e -> modelMapper.map(e, EmpleadoDTO.class))
                .collect(Collectors.toList());
        return  empleadoDTOList;
    }

    public List<Empleado> getEmpleadosDeTiendasActivos() {
        return repository.getEmpleadosActivos();
    }
}
