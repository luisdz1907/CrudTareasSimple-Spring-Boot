package com.todosistemas.crudtareas.Services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.todosistemas.crudtareas.Models.Empleado;
import com.todosistemas.crudtareas.Repository.EmpleadoRepository;
import com.todosistemas.crudtareas.Services.IEmpleadoService;

@Service
public class EmpleadoService implements IEmpleadoService{

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Empleado> findAll() {

        return (List<Empleado>) empleadoRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Empleado findById(long id) {
        return empleadoRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Empleado save(Empleado empleado) {
        return empleadoRepository.save(empleado);
    }

    @Override
    @Transactional
    public void delete(long id) {
        empleadoRepository.deleteById(id);
    }

}
