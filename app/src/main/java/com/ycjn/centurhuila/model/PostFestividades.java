package com.ycjn.centurhuila.model;

/**
 * Created by Norbey on 05/05/2018.
 */

public class PostFestividades {
    private String tituloFiesta;
    private String descFiesta;

    public PostFestividades() {
    }

    public PostFestividades(String tituloFiesta, String descFiesta) {
        this.tituloFiesta = tituloFiesta;
        this.descFiesta = descFiesta;
    }

    public String getTituloFiesta() {
        return tituloFiesta;
    }

    public void setTituloFiesta(String tituloFiesta) {
        this.tituloFiesta = tituloFiesta;
    }

    public String getDescFiesta() {
        return descFiesta;
    }

    public void setDescFiesta(String descFiesta) {
        this.descFiesta = descFiesta;
    }
}
