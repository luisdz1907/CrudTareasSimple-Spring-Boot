package com.todosistemas.crudtareas.Models;




import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tbl_actividad")
public class Actividad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nombreActividad;
    private String estatus;
    
    @javax.persistence.Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "MM-dd-yy")
    private Date fechaEjecucion;

    private String horaEjecucion;
    private String nombreAsignado;

    @ManyToOne
    @JoinColumn(name = "empleado_id")
    private Empleado empleado;
   

    @PrePersist
    public void perPersist(){
        fechaEjecucion = new Date();
    }
}
