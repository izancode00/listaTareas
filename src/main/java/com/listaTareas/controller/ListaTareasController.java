package com.listaTareas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.listaTareas.model.Tarea;
import com.listaTareas.service.TareaService;

@Controller

@RequestMapping("/lista")
public class ListaTareasController {
    private final TareaService tareaService;

    public ListaTareasController(TareaService tareasService) {
        this.tareaService = tareasService;
    }

    @GetMapping("")
    public String mostrarTarea(Model model, @ModelAttribute("message") String mensaje){
        model.addAttribute("lista", tareaService.obtenerTareasPendientes());
        model.addAttribute("lista", tareaService.obtenerTareasFinalizadas());
        model.addAttribute("message",mensaje);
        return "lista";
    }

    @GetMapping("/nuevaTarea")
    public String nuevoContactoForm(Model model){
        model.addAttribute("tarea", new Tarea());
        return "nuevaTarea";
    }

    @PostMapping("/guardar")
    public String añadirTarea(@ModelAttribute Tarea tarea, RedirectAttributes redirectAttributes){
        tareaService.añadirTarea(tarea.getNombre(), tarea);
        redirectAttributes.addFlashAttribute("guardar", "Tarea guardada con éxito");
        return "redirect:/lista";
    }

    @PostMapping("/eliminar/{nombre}")
    public String eliminarTarea(@PathVariable("nombre") String nombre, RedirectAttributes redirectAttributes){
        boolean eliminado = tareaService.eliminarTarea(nombre);
        if(eliminado){
            redirectAttributes.addAttribute("message", "Tarea eliminada con éxito");
        }else{
            redirectAttributes.addFlashAttribute("message", "No se pudo eliminar la tarea");
        }
        return "redirect:/lista";
    }

    @GetMapping("/editar/{nombre}")
    public String editarTarea(@PathVariable("nombre") String nombre, Model model){
        Tarea tarea = tareaService.obtenerTareaPorNombre(nombre);
        model.addAttribute("tarea", tarea);
        return "editarTarea";
    }

    @PostMapping("/actualizar/{nombre}")
    public String actualizarTarea(@PathVariable("nombre") String nombreOriginal, Tarea tareaActualizada, RedirectAttributes redirectAttributes){
        tareaService.editarTarea(nombreOriginal);
        redirectAttributes.addFlashAttribute("message", "Tarea actualizada con éxito");
        return "redirect:/lista";

    }

    @PostMapping("/finalizada/{nombre}")
    public String finalizarTarea(@PathVariable("nombre") String nombre, RedirectAttributes redirectAttributes){
        tareaService.terminarTarea(nombre);
        redirectAttributes.addFlashAttribute("message", "Tarea finalizada");
        return "redirect:/lista";
    }






    
}
