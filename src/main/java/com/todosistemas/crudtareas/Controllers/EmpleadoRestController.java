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

import com.todosistemas.crudtareas.Models.Empleado;
import com.todosistemas.crudtareas.Services.IEmpleadoService;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("api")
public class EmpleadoRestController {
    // Obtenemos el listado de los empleados
    @Autowired
    private IEmpleadoService empleadoService;

    // Listamos los empleados
    @GetMapping("/empleados")
     public List<Empleado> index(){
        return empleadoService.findAll();
     }

   //Mostrar por ID
   @GetMapping("/empleados/{id}")
   public ResponseEntity<?> show (@PathVariable Long id){
      Empleado empleado = null;

      Map<String, Object> response = new HashMap<>();

      try{
         empleado = empleadoService.findById(id);
      }catch(DataAccessException e){

         response.put("mensaje", "Error al realizar la consulta en la base de datos.");
         response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));

         return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
      }

      if (empleado == null) {
         response.put("mensaje", "El empleado con el  ID: ".concat(id.toString().concat(" no existe en la base de datos.")));

         return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
      }

      return new ResponseEntity<Empleado>(empleado, HttpStatus.OK);
   }

   //Crear Empleado
   @PostMapping("/empleados")
   public ResponseEntity<?> create (@RequestBody Empleado empleado){
      Empleado empleadoNew= null;

      Map<String, Object> response = new HashMap<>();

      try{
         empleadoNew = empleadoService.save(empleado);
      }catch(DataAccessException e){

         response.put("mensaje", "Error al guardar el empleado en la base de datos.");
         response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));

         return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
      }

      
      response.put("mensaje", "El empleado creada con exito.");
      response.put("empleado", empleadoNew);

      return new ResponseEntity<Empleado>(empleadoNew, HttpStatus.CREATED);
   }

   //Actualizar
   @PutMapping("/empleados/{id}")
   public ResponseEntity<?> update (@RequestBody Empleado empleado, @PathVariable Long id){

      Empleado empleadoActual = empleadoService.findById(id);
      Empleado empleadoUpdate= null;

      Map<String, Object> response = new HashMap<>();

      //Validamos que exista en la base de datos
      if (empleadoActual == null) {
         response.put("mensaje", "Error no se pudo actualizar, el empleado con el ID: ".concat(id.toString().concat(" no existe en la base de datos.")));

         return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
      }

      try{

         empleadoActual.setNombre(empleado.getNombre());
         empleadoActual.setApellidos(empleado.getApellidos());
         empleadoActual.setCedula(empleado.getCedula());
         empleadoActual.setTelefono(empleado.getTelefono());
         empleadoActual.setDireccion(empleado.getDireccion());
         empleadoActual.setFechaNaciemiento(empleado.getFechaNaciemiento());
  


         empleadoUpdate = empleadoService.save(empleadoActual);
      }catch(DataAccessException e){

         response.put("mensaje", "Error al actualizar el empleado en la base de datos.");
         response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));

         return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
      }

      //Si la actualizada fue creada con exito
      response.put("mensaje", "Empleado actualizada con exito.");
      response.put("empleado", empleadoUpdate);

      return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
   }

    // Eliminar
    @DeleteMapping("/empleados/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();

        try {
            empleadoService.delete(id);

        } catch (DataAccessException e) {
            response.put("mensaje", "Error al eliminar en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));

            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        // Si el cliente es eliminado
        response.put("mensaje", "El empleado fue eliminado con exito.");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

}
