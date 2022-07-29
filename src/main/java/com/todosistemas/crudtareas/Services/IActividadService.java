package com.todosistemas.crudtareas.Services;

import java.util.List;

import com.todosistemas.crudtareas.Models.Actividad;

public interface IActividadService {
     public List<Actividad> findAll();

     public Actividad findById(long id);

     public Actividad save(Actividad actividad);

     public void delete(long id);


}
