package com.ycjn.centurhuila.entidades;

/**
 * Created by CAMILO on 4/4/2018.
 */

public class Usuario {

    private  Integer id;
    private String nombre;

    public Usuario (Integer id,String nombre){
        this.id=id;
        this.nombre=nombre;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
