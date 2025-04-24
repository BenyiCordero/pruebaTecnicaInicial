package com.booster.pruebaTecnica.task;

import java.util.List;

/**
 * Interfaz para que nuestra app sea mas escalable, colocamos las firmas de las funciones que tendra nuestra app,
 * generalmente para el CRUD
 */
public interface TaskService {

    Task createTask (Task task);
    Task getTaskById(Long id);
    Task updateTask(Task task);
    List<Task> getAllTasks ();
    void deleteTaskById(Long id);
}
