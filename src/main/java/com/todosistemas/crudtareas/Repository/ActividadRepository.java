package com.todosistemas.crudtareas.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.todosistemas.crudtareas.Models.Actividad;
@Repository
public interface ActividadRepository extends CrudRepository<Actividad, Long>{
    
}
