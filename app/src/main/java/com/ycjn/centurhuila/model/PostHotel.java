package com.ycjn.centurhuila.model;

/**
 * Created by Norbey on 22/04/2018.
 */

public class PostHotel {
    private String nombre;
    private String direccion;
    private String imagen;
    private String descripcion;
    private String urlpagina;
    private String telefono;
    private String correo;
    private String disponible;
    private double lactitud;
    private double longitud;

    public PostHotel() {
    }

    public PostHotel(String nombre, String direccion, String imagen, String descripcion, String urlpagina, String telefono, String correo, String disponible, double lactitud, double longitud) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.imagen = imagen;
        this.descripcion = descripcion;
        this.urlpagina = urlpagina;
        this.telefono = telefono;
        this.correo = correo;
        this.disponible = disponible;
        this.lactitud = lactitud;
        this.longitud = longitud;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUrlpagina() {
        return urlpagina;
    }

    public void setUrlpagina(String urlpagina) {
        this.urlpagina = urlpagina;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDisponible() {
        return disponible;
    }

    public void setDisponible(String disponible) {
        this.disponible = disponible;
    }

    public double getLactitud() {
        return lactitud;
    }

    public void setLactitud(double lactitud) {
        this.lactitud = lactitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }
}
