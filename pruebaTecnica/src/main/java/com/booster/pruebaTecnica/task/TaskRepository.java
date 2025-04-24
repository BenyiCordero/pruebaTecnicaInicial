package com.booster.pruebaTecnica.task;

import org.springframework.data.jpa.repository.JpaRepository;
/**
 * Interfaz del repositorio de tarea que se extiende al Jpa y nos permite tener funciones como findById, findAll y
 * muchos mas metodos con los que nos permite hacer un CRUD mas limpio
 */
public interface TaskRepository extends JpaRepository<Task, Long> {
}
