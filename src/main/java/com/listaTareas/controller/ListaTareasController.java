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

@Controller
@RequestMapping("/listaTareas")
public class ListaTareasController {
    private final ListaTareasService listaTareasService;

    public ListaTareasController(ListaTareasService listaTareasService) {
        this.listaTareasService = listaTareasService;
    }

    @GetMapping("")
    public String mostrarTarea(Model model, @ModelAttribute("message") String mensaje){
        // model.addAttribute("agenda", contactoService.obtenerContacto());
        model.addAttribute("message",mensaje);
        return "listaTareas";
    }

    @GetMapping("/nueva")
    public String nuevoContactoForm(Model model){
        model.addAttribute("tarea", new Tarea());
        return "nuevaTarea";
    }

    @PostMapping("/guardar")
    public String guardarContacto(@ModelAttribute Tarea tarea, RedirectAttributes redirectAttributes){
        //contactoService.guardarContacto(contacto);
        redirectAttributes.addFlashAttribute("guardar", "Tarea guardada con éxito");
        return "redirect:/listaTareas";
    }

    @PostMapping("/eliminar/{nombre}")
    public String eliminarContacto(@PathVariable("nombre") String nombre, RedirectAttributes redirectAttributes){
        boolean eliminado = listaTareasService.eliminarTarea(nombre);
        if(eliminado){
            redirectAttributes.addAttribute("message", "Tarea eliminada con éxito");
        }else{
            redirectAttributes.addFlashAttribute("message", "No se pudo eliminar la tarea");
        }
        return "redirect:/listaTareas";
    }

    @GetMapping("/editar/{nombre}")
    public String editarContacto(@PathVariable("nombre") String nombre, Model model){
        Tarea tarea = listaTareasService.obtenerContactoPorNombre(nombre);
        model.addAttribute("tarea", tarea);
        return "editarTarea";
    }

    @PostMapping("/actualizar/{nombre}")
    public String actualizarContacto(@PathVariable("nombre") String nombreOriginal, Tarea tareaActualizada, RedirectAttributes redirectAttributes){
        listaTareasService.actualizarContacto(nombreOriginal, tareaActualizada);
        redirectAttributes.addFlashAttribute("message", "Tarea actualizada con éxito");
        return "redirect:/listaTareas";
    }

    @PostMapping("/finalizada/{nombre}")
    public String finalizarTarea(@PathVariable("nombre") String nombre, RedirectAttributes redirectAttributes){
        listaTareasService.finalizarTarea(nombre);
        redirectAttributes.addFlashAttribute("message", "Tarea finalizada");
        return "redirect:/listaTareas";
    }






    
}
