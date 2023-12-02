package com.caracol.zunpr.repository;

import com.caracol.zunpr.configs.mapper.EmpleadoMapper;
import com.caracol.zunpr.model.Empleado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmpleadoRepository implements IEmpleadoRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public EmpleadoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Empleado> getEmpleadosActivos() {
        StringBuilder query = new StringBuilder("SELECT")
                .append(" e.no_interno as id,")
                .append("e.nombre,")
                .append("e.apell1 as primerApellido,")
                .append("e.apell2 as segundoApellido,")
                .append("e.no_expediente as ci,")
                .append("e.sexo,")
                .append("e.color_piel,")
                .append("e.telefono,")
                .append("e.fecha_nac as fechaNacimiento,")
                .append("uo.descripcion as unidadOrganizacional,")
                .append("ge.salario,")
                .append("ge.iden_grupo_sal as grupoSalarial,")
                .append("c.descripcion as cargo,")
                .append("e.direccion,")
                .append("m.descripcion as municipio,")
                .append("p.descripcion as provincia ")
                .append("FROM p_empleado e LEFT JOIN n_municipios m on e.id_municipio = m.id_municip ")
                .append("LEFT JOIN n_provincias p on m.cod_prov = p.id_prov ")
                .append("JOIN n_cargos c on c.id_cargos = e.cargo ")
                .append("JOIN n_unidad_org uo on uo.id_uorg = e.unidad_org ")
                .append("JOIN n_grupo_escala ge on ge.id_grupo_sal = e.grupo_escala ")
                .append("WHERE e.activo = 1");
        return jdbcTemplate.query(query.toString(), new EmpleadoMapper());
        //return jdbcTemplate.query(query.toString(), BeanPropertyRowMapper.newInstance(Empleado.class));
    }
}
