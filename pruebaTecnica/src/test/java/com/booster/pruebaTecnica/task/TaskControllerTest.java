package com.booster.pruebaTecnica.task;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
/**
 * Pruebas de integración para el controlador de tareas {@link TaskController}.
 * <p>
 * Esta clase prueba los endpoints REST para operaciones CRUD sobre tareas,
 * utilizando {@link MockMvc} para simular solicitudes HTTP.
 * </p>
 */
@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private TaskService taskService;

    private Task testTask;

    @BeforeEach
    void setUp (){
        testTask = new Task();
        testTask.setTitle("Titulo de prueba");
        testTask.setDescription("Tarea de prueba");
        testTask.setState("pendiente");
        taskService.createTask(testTask);
    }

    @Test
    void testGetAllTasks() throws Exception {
        mockMvc.perform(get("/tareas/{id}", testTask.getId()))
                .andExpect(status().isOk());
    }

    @Test
    void testGetTaskById() throws Exception{
        mockMvc.perform(get("/tareas/{id}", testTask.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.description")
                        .value("Tarea de prueba"))
                .andExpect(jsonPath("$.title")
                        .value("Titulo de prueba"))
                .andExpect(jsonPath("$.state")
                        .value("pendiente"));
    }

    @Test
    void testCreateTask () throws Exception{
        Task nuevaTarea = new Task();
        nuevaTarea.setTitle("Nueva tarea");
        nuevaTarea.setDescription("Nueva tarea");
        nuevaTarea.setState("en progreso");

        mockMvc.perform(post("/tareas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(nuevaTarea)))
                .andExpect(status().isCreated());
    }

    @Test
    void testDeleteTask () throws Exception{
        mockMvc.perform(delete("/tareas/{id}", testTask.getId()))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .string("Tarea eliminada con id " + testTask.getId()));
    }

    @Test
    void testUpdateTask () throws Exception{
        testTask.setTitle("Tarea actualizada");
        testTask.setDescription("Descripcion actualizada");
        testTask.setState("completada");

        mockMvc.perform(patch("/tareas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testTask)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .string("Tarea modificada con exito"));
    }

}
