package com.booster.pruebaTecnica.task;

import java.util.List;

public interface TaskService {

    Task createTask (Task task);
    Task getTaskById(Long id);
    Task updateTask(Task task);
    List<Task> getAllTasks ();
    void deleteTaskById(Long id);
}
