package com.listaTareas.model;

public class Tarea {
    private String nombre;
    private String contenido;
    private String comentario;
    
    public Tarea() {}

    public Tarea(String nombre, String contenido, String comentario) {
        this.nombre = nombre;
        this.contenido = contenido;
        this.comentario = comentario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    


    
    
}
