package com.ycjn.centurhuila.model;

/**
 * Created by Norbey on 16/04/2018.
 */

public class PostInformacion {
    private String temperatura;
    private String altura;
    private String poblacion;
    private String gentilicio;
    private String descripcion;

    private String images;
    private String descimages;

    private String historia;

    private double lactitud;
    private double longitud;
    private String terrestre;
    private String aerea;

    public PostInformacion() {
    }

    public PostInformacion(String temperatura, String altura, String poblacion, String gentilicio, String descripcion, String images, String descimages, String historia, double lactitud, double longitud, String terrestre, String aerea) {
        this.temperatura = temperatura;
        this.altura = altura;
        this.poblacion = poblacion;
        this.gentilicio = gentilicio;
        this.descripcion = descripcion;
        this.images = images;
        this.descimages = descimages;
        this.historia = historia;
        this.lactitud = lactitud;
        this.longitud = longitud;
        this.terrestre = terrestre;
        this.aerea = aerea;
    }

    public String getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(String temperatura) {
        this.temperatura = temperatura;
    }

    public String getAltura() {
        return altura;
    }

    public void setAltura(String altura) {
        this.altura = altura;
    }

    public String getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(String poblacion) {
        this.poblacion = poblacion;
    }

    public String getGentilicio() {
        return gentilicio;
    }

    public void setGentilicio(String gentilicio) {
        this.gentilicio = gentilicio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getDescimages() {
        return descimages;
    }

    public void setDescimages(String descimages) {
        this.descimages = descimages;
    }

    public String getHistoria() {
        return historia;
    }

    public void setHistoria(String historia) {
        this.historia = historia;
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

    public String getTerrestre() {
        return terrestre;
    }

    public void setTerrestre(String terrestre) {
        this.terrestre = terrestre;
    }

    public String getAerea() {
        return aerea;
    }

    public void setAerea(String aerea) {
        this.aerea = aerea;
    }
}
