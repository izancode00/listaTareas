package com.listaTareas.service;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Service;
import com.listaTareas.model.Tarea;

@Service
public class TareaService {

    private Map<String, Tarea> tareasPendientes = new HashMap<>();
    private Map<String, Tarea> tareasTerminadas = new HashMap<>();

    public Map<String, Tarea> obtenerTareasPendientes(){
        return tareasPendientes;
    }

    public Map<String, Tarea> obtenerTareasFinalizadas(){
        return tareasTerminadas;
    }

    // Método para añadir una tarea a tareasPendientes
    public void añadirTarea(String nombre, Tarea tarea) {
        tareasPendientes.put(nombre, tarea);
    }

    // Método para mover una tarea de tareasPendientes a tareasTerminadas
    public void terminarTarea(String nombre) {
        Tarea tarea = tareasPendientes.remove(nombre);
        if (tarea != null) {
            tareasTerminadas.put(nombre, tarea);
        }
    }

    //editar por nombre
    public void editarTarea(String id) {
        Tarea tarea = tareasPendientes.remove(id);
        if (tarea != null) {
            tareasTerminadas.put(id, tarea);
        }
    }
//obtener por nombre
public Tarea obtenerTareaPorNombre(String nombre) {
    return tareasPendientes.get(nombre);
}
//borrar
public boolean eliminarTarea(String nombre){
    Tarea tarea = tareasPendientes.get(nombre);
    if(tarea != null){
        tareasPendientes.remove(nombre);
        return true;
    }else if (tareasPendientes.get(nombre) != null){
    tareasTerminadas.remove(nombre);
    return true;
    }else{
        return false;
    }

    }
}
