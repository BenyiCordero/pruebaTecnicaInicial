package com.booster.pruebaTecnica.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * Este sera el encargado de brindar el servicio para crear las tareas, esta es una clase la cual implementa todos los
 * metodos y funciones que se pusieron en nuestra interfaz TareaService y desde aqui haremos la peticion hacia el
 * repositorio
 */
@Service
public class TaskServiceImpl implements TaskService{
    //Instancia de la clase, la cual nos permitira llamar a nuestro repositorio y sus metodos o funciones
    @Autowired
    private TaskRepository taskRepository;

    //Funcion que nos permite crear una tarea, esta funcion recibe el formato de tarea
    @Override
    public Task createTask(Task task) {
        taskRepository.save(task); //Para guardar la tarea en la base de datos se utiliza save
        return task;
    }

    //Funcion que nos permite mostrar una tarea determinada por su id
    @Override
    public Task getTaskById(Long id) {
        return taskRepository.findById(id).orElse(null);//Hacemos una lista y llamamos a nuestro metodo de tareas
    }

    @Override
    public Task updateTask(Task tarea) {
        List<Task> listaTareas = getAllTasks();
        for (Task t : listaTareas){
            if (t.getId().equals(tarea.getId())){
                if (tarea.getTitle() != null){
                    t.setTitle(tarea.getTitle());
                }
                if (tarea.getDescription() != null){
                    t.setDescription(tarea.getDescription());
                }
                if (tarea.getState() != null){
                    t.setState(tarea.getState());
                }
                return taskRepository.save(t);
            }
        }
        return null;
    }

    //Metodo que nos permite mostrar todas las tareas que existen en nuestra base de datos
    @Override
    public List<Task> getAllTasks() {
        List<Task> taskList = taskRepository.findAll(); //Creamos una lista y llamamos al metodo findAll de nuestro repo
        return taskList;//Retornamos la lista que tendra ahora todos los datos de nuestra base de datos
    }

    //Metodo para eliminar una tarea por medio de su id
    @Override
    public void deleteTaskById(Long id) {
        if (!taskRepository.existsById(id)){ //Comprobamos que la tarea exista en nuestra base de datos
            throw new RuntimeException(); //Si no existe, mandamos una exception
        }
        taskRepository.deleteById(id); //Si si existe, mandamos llamar a el metodo de nuestro repo y eliminamos
    }


}
