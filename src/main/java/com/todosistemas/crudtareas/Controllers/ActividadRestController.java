package com.todosistemas.crudtareas.Controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todosistemas.crudtareas.Models.Actividad;
import com.todosistemas.crudtareas.Services.IActividadService;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("api")
public class ActividadRestController {
    // Obtenemos el listado de actividades
    @Autowired
    private IActividadService actividadService;
    @Autowired

    // Listamos los clientes
    @GetMapping("/actividades")
     public List<Actividad> index(){

        return actividadService.findAll();
     }

   //Mostrar por ID
   @GetMapping("/actividades/{id}")
   public ResponseEntity<?> show (@PathVariable Long id){
      Actividad actividad = null;

      Map<String, Object> response = new HashMap<>();

      try{
         actividad = actividadService.findById(id);
      }catch(DataAccessException e){

         response.put("mensaje", "Error al realizar la consulta en la base de datos.");
         response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));

         return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
      }

      if (actividad == null) {
         response.put("mensaje", "La actividad ID: ".concat(id.toString().concat(" no existe en la base de datos.")));

         return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
      }

      return new ResponseEntity<Actividad>(actividad, HttpStatus.OK);
   }

   //Crear Actividad
   @PostMapping("/actividades")
   public ResponseEntity<?> create (@RequestBody Actividad actividad){
      Actividad actividadNew = null;
      
      Map<String, Object> response = new HashMap<>();

      try{
         actividadNew = actividadService.save(actividad);
      }catch(DataAccessException e){

         response.put("mensaje", "Error al guardar la actividad en la base de datos.");
         response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));

         return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
      }

      //Si la actividad fue creada con exito
      response.put("mensaje", "Actividad creada con exito.");
      response.put("actividad", actividadNew);

      return new ResponseEntity<Actividad>(actividadNew, HttpStatus.CREATED);
   }

   //Actualizar
   @PutMapping("/actividades/{id}")
   public ResponseEntity<?> update (@RequestBody Actividad actividad, @PathVariable Long id){

      Actividad actividadActual = actividadService.findById(id);//Obtenemos el id actual de la actividad
      Actividad actividadUpdate= null;

      Map<String, Object> response = new HashMap<>();

      //Validamos que exista en la base de datos
      if (actividadActual == null) {
         response.put("mensaje", "Error no se pudo actualizar, la actividad ID: ".concat(id.toString().concat(" no existe en la base de datos.")));

         return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
      }

      try{

         actividadActual.setNombreActividad(actividad.getNombreActividad());
         actividadActual.setEstatus(actividad.getEstatus());
         actividadActual.setFechaEjecucion(actividad.getFechaEjecucion());
         actividadActual.setHoraEjecucion(actividad.getHoraEjecucion());
         actividadActual.setNombreAsignado(actividad.getNombreAsignado());


         actividadUpdate = actividadService.save(actividadActual);
      }catch(DataAccessException e){

         response.put("mensaje", "Error al actualizar la actividad en la base de datos.");
         response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));

         return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
      }

      //Si la actualizada fue creada con exito
      response.put("mensaje", "Actividad actualizada con exito.");
      response.put("actividad", actividadUpdate);

      return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
   }

    // Eliminar
    @DeleteMapping("/actividades/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();

        try {
            actividadService.delete(id);

        } catch (DataAccessException e) {
            response.put("mensaje", "Error al eliminar en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));

            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        // Si el cliente es eliminado
        response.put("mensaje", "La actividad fue eliminado con exito.");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

}
