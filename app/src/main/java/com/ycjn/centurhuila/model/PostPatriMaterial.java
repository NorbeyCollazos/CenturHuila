package com.ycjn.centurhuila.model;

/**
 * Created by Norbey on 27/04/2018.
 */

public class PostPatriMaterial {
    private String nombre;
    private String imagen;
    private String direccion;
    private String descripcion;
    private String vereda;
    private String propietario;
    private String telefono;
    private String indicaciones;
    private double lat;
    private double lon;

    public PostPatriMaterial() {
    }

    public PostPatriMaterial(String nombre, String imagen, String direccion, String descripcion, String vereda, String propietario, String telefono, String indicaciones, double lat, double lon) {
        this.nombre = nombre;
        this.imagen = imagen;
        this.direccion = direccion;
        this.descripcion = descripcion;
        this.vereda = vereda;
        this.propietario = propietario;
        this.telefono = telefono;
        this.indicaciones = indicaciones;
        this.lat = lat;
        this.lon = lon;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getVereda() {
        return vereda;
    }

    public void setVereda(String vereda) {
        this.vereda = vereda;
    }

    public String getPropietario() {
        return propietario;
    }

    public void setPropietario(String propietario) {
        this.propietario = propietario;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getIndicaciones() {
        return indicaciones;
    }

    public void setIndicaciones(String indicaciones) {
        this.indicaciones = indicaciones;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }
}
