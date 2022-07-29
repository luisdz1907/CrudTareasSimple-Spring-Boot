package com.todosistemas.crudtareas.Models;



import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tbl_empleado")
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nombre;
    private String apellidos;
    private String cedula;
    private String telefono;
    private String direccion;
    private String fechaNaciemiento;
    @JsonIgnore
    @OneToMany(mappedBy = "empleado")
    List<Actividad> actividad;

}
