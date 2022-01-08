package com.ycjn.centurhuila.model;

/**
 * Created by Norbey on 20/04/2018.
 */

public class PostSitiosNaturales {
    private String nombre;
    private String direccion;
    private String imagen1;
    private String imagen2;
    private String imagen3;
    private String imagen4;
    private String descimagen1;
    private String descimagen2;
    private String descimagen3;
    private String descimagen4;
    private String datosbasicos;
    private String recomendaciones;
    private double lactitud;
    private double longitud;
    private String viaterrestre;

    public PostSitiosNaturales() {
    }

    public PostSitiosNaturales(String nombre, String direccion, String imagen1, String imagen2, String imagen3, String imagen4, String descimagen1, String descimagen2, String descimagen3, String descimagen4, String datosbasicos, String recomendaciones, double lactitud, double longitud, String viaterrestre) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.imagen1 = imagen1;
        this.imagen2 = imagen2;
        this.imagen3 = imagen3;
        this.imagen4 = imagen4;
        this.descimagen1 = descimagen1;
        this.descimagen2 = descimagen2;
        this.descimagen3 = descimagen3;
        this.descimagen4 = descimagen4;
        this.datosbasicos = datosbasicos;
        this.recomendaciones = recomendaciones;
        this.lactitud = lactitud;
        this.longitud = longitud;
        this.viaterrestre = viaterrestre;
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

    public String getImagen1() {
        return imagen1;
    }

    public void setImagen1(String imagen1) {
        this.imagen1 = imagen1;
    }

    public String getImagen2() {
        return imagen2;
    }

    public void setImagen2(String imagen2) {
        this.imagen2 = imagen2;
    }

    public String getImagen3() {
        return imagen3;
    }

    public void setImagen3(String imagen3) {
        this.imagen3 = imagen3;
    }

    public String getImagen4() {
        return imagen4;
    }

    public void setImagen4(String imagen4) {
        this.imagen4 = imagen4;
    }

    public String getDescimagen1() {
        return descimagen1;
    }

    public void setDescimagen1(String descimagen1) {
        this.descimagen1 = descimagen1;
    }

    public String getDescimagen2() {
        return descimagen2;
    }

    public void setDescimagen2(String descimagen2) {
        this.descimagen2 = descimagen2;
    }

    public String getDescimagen3() {
        return descimagen3;
    }

    public void setDescimagen3(String descimagen3) {
        this.descimagen3 = descimagen3;
    }

    public String getDescimagen4() {
        return descimagen4;
    }

    public void setDescimagen4(String descimagen4) {
        this.descimagen4 = descimagen4;
    }

    public String getDatosbasicos() {
        return datosbasicos;
    }

    public void setDatosbasicos(String datosbasicos) {
        this.datosbasicos = datosbasicos;
    }

    public String getRecomendaciones() {
        return recomendaciones;
    }

    public void setRecomendaciones(String recomendaciones) {
        this.recomendaciones = recomendaciones;
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

    public String getViaterrestre() {
        return viaterrestre;
    }

    public void setViaterrestre(String viaterrestre) {
        this.viaterrestre = viaterrestre;
    }
}
