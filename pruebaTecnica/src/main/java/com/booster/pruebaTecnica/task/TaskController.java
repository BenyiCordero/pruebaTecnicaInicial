package com.booster.pruebaTecnica.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * Controlador de tareas, aqui llamamos a la interfaz TareaService y a sus metodos que puse ahi
 * Tambien marcamos la ruta general como /tareas1
 */
@RestController
@RequestMapping("/tareas")
public class TaskController {

    @Autowired //Para hacer nuestra instancia de clase, la cual nos servira para llamar a los metodos
    private TaskService taskService;

    //Funcion que devuelve una lista de todas las tareas existentes con el metodo get y ruta /tareas1
    @GetMapping
    public ResponseEntity<?> conseguirTodasTareas (){
        List<Task> listaTareas = taskService.getAllTasks(); //Defiinimos la lista y llamamos a nuestro servicio
        if (listaTareas.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); //Si la lista esta vacia devolvemos un no content
        }else return ResponseEntity.ok(listaTareas); //Si no esta vacia, devolvemos la lista junto con un estado ok
    }


    //Funcion que devuelve mi tarea que se encuentra con un id especifico
    @GetMapping ("/{id}")
    public ResponseEntity<?> conseguirTareaId (@PathVariable Long id){
        Task tarea = taskService.getTaskById(id); //Creamos una tarea llamando al metodo con nuestro servicio
        if (tarea == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tarea no existente"); //Si la tarea no existe salta
        }else return ResponseEntity.ok(tarea); //Si si existe aqui se muestra
    }

    //Funcion que nos permite crear una nueva tarea pidiendo el formato Json
    @PostMapping
    public ResponseEntity<?> crearTarea (@RequestBody Task task){
        taskService.createTask(task); //Llamamos a nuestra funcion por medio de el servicio y mandamos la tarea
        return ResponseEntity.status(HttpStatus.CREATED).body("Tarea creada"); //Mandamos el codigo de creado y un mensaje
    }

    //Funcion que nos permite eliminar una tarea por medio de su id
    @DeleteMapping ("/{id}")
    public ResponseEntity<?> eliminarTareaId (@PathVariable Long id){
        try {
            taskService.deleteTaskById(id); //Intentamos hacer el bloque de codigo, y mandamos llamar a nuestra funcion
            return ResponseEntity.status(HttpStatus.OK).body("Tarea eliminada con id " + id); //Retornamos un ok y mensaje
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tarea no encontrada"); //Si salta exception se ejecuta
        }
    }

    @PatchMapping
    public ResponseEntity<?> actualizarTareaId (@RequestBody Task task){
        if (taskService.updateTask(task) == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tarea no encontrada");
        }
        return ResponseEntity.status(HttpStatus.OK).body("Tarea modificada con exito");
    }

}
