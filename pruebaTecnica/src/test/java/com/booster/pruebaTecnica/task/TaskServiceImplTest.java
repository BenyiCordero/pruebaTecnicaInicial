package com.booster.pruebaTecnica.task;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.never;
/**
 * Pruebas unitarias para la clase {@link TaskServiceImpl}.
 * <p>
 * Se utilizan mocks para simular la lógica del {@link TaskRepository} y
 * verificar que {@link TaskServiceImpl} interactúe correctamente con él.
 * </p>
 */
public class TaskServiceImplTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskServiceImpl taskService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllTasks(){
        Task tarea1 = new Task(1L, "Tarea 1", "Descripcion tarea 1", "pendiente");
        Task tarea2 = new Task(2L, "Tarea 2","Descripcion tarea 2", "completada");
        when(taskRepository.findAll()).thenReturn(Arrays.asList(tarea1,tarea2));

        List<Task> result = taskService.getAllTasks();

        assertEquals(2, result.size());
        verify(taskRepository, times(1)).findAll();
    }


    @Test
    void testGetTaskByIdFound (){
        Task tarea = new Task(1L, "Tarea encontrada", "Descripcion tarea encontrada","pendiente");
        when(taskRepository.findById(1L)).thenReturn(Optional.of(tarea));

        Task result = taskService.getTaskById(1l);

        assertNotNull(result);
        assertEquals("Descripcion tarea encontrada", result.getDescription());
        verify(taskRepository).findById(1L);
    }

    @Test
    void testGetTaskByIdNotFound (){
        when(taskRepository.findById(99L)).thenReturn(Optional.empty());

        Task result = taskService.getTaskById(99L);

        assertNull(result);
        verify(taskRepository).findById(99L);
    }

    @Test
    void testCreateTask (){
        Task nuevaTarea = new Task(null, "Tarea nueva", "Descripcion tarea nueva", "pendiente");
        taskService.createTask(nuevaTarea);

        verify(taskRepository).save(nuevaTarea);
    }

    @Test
    void testDeleteTaskById (){
        Task tarea = new Task(1L, "Tarea", "Descripcion", "pendiente");

        when(taskRepository.existsById(1L)).thenReturn(true);

        taskService.deleteTaskById(1L);

        verify(taskRepository).deleteById(1L);
    }

    @Test
    void testUpdateTaskSuccess(){
        Task existente = new Task(1L, "Vieja", "Descripcion tarea vieja", "pendiente");
        Task nueva = new Task(1L, "Nueva", "Descripcion tarea nueva", "completada");

        // Simula lo que usa tu método: getAllTasks() → taskRepository.findAll()
        when(taskRepository.findAll()).thenReturn(List.of(existente));
        when(taskRepository.save(any(Task.class))).thenReturn(nueva);

        Task result = taskService.updateTask(nueva);

        assertNotNull(result);
        assertEquals("Nueva", result.getTitle());
        assertEquals("completada", result.getState());
        verify(taskRepository).save(any(Task.class));
    }

    @Test
    void testUpdateTaskNotFound (){
        Task actualizada = new Task (999L, "Nada", "Nada","false");

        when (taskRepository.findById(999L)).thenReturn(Optional.empty());

        Task result = taskService.updateTask(actualizada);

        assertNull(result);
        verify(taskRepository, never()).save(any(Task.class));
    }


}
