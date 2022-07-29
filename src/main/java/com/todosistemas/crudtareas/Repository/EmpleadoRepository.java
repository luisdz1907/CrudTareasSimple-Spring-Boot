package com.todosistemas.crudtareas.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.todosistemas.crudtareas.Models.Empleado;
@Repository
public interface EmpleadoRepository extends CrudRepository<Empleado, Long> {
    
}
