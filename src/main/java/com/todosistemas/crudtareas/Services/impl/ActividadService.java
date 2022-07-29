package com.todosistemas.crudtareas.Services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.todosistemas.crudtareas.Models.Actividad;
import com.todosistemas.crudtareas.Repository.ActividadRepository;
import com.todosistemas.crudtareas.Services.IActividadService;

@Service
public class ActividadService implements IActividadService {

    @Autowired
    private ActividadRepository actividadRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Actividad> findAll() {

        return (List<Actividad>) actividadRepository.findAll();

    }
 

    @Override
    @Transactional(readOnly = true)
    public Actividad findById(long id) {
        return actividadRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Actividad save(Actividad actividad) {
        
        return actividadRepository.save(actividad);
    }

    @Override
    @Transactional
    public void delete(long id) {
        actividadRepository.deleteById(id);
    }


}
