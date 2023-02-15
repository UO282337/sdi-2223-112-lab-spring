package com.uniovi.notaneitor.entities;

public class Professor {
    private String dni;
    private String nombre;
    private String apellidos;
    private String categoria;
    public Professor() {}

    public Professor(String dni, String nombre, String apellidos, String categoria) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "Professor{" +
                "dni=" + dni + ", " +
                "nombre=" + nombre + ", " +
                "apellidos=" + apellidos + ", " +
                "categoria=" + categoria + "}";
    }

    public String getDni() {
        return dni;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
