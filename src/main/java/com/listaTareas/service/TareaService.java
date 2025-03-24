package com.listaTareas.service;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Service;
import com.listaTareas.model.Tarea;

@Service
public class TareaService {

    private Map<String, Tarea> tareasPendientes = new HashMap<>();
    private Map<String, Tarea> tareasTerminadas = new HashMap<>();

    // Método para añadir una tarea a tareasPendientes
    public void añadirTarea(String id, Tarea tarea) {
        tareasPendientes.put(id, tarea);
    }

    // Método para mover una tarea de tareasPendientes a tareasTerminadas
    public void terminarTarea(String id) {
        Tarea tarea = tareasPendientes.remove(id);
        if (tarea != null) {
            tareasTerminadas.put(id, tarea);
        }
    }

    // Métodos adicionales para gestionar las tareas pueden ser añadidos aquí
}
