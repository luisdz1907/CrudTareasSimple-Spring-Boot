package com.todosistemas.crudtareas.Services;

import java.util.List;

import com.todosistemas.crudtareas.Models.Empleado;

public interface IEmpleadoService {
    public List<Empleado> findAll();

    public Empleado findById(long id);

    public Empleado save(Empleado empleado);

    public void delete(long id);
}
