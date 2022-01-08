package com.ycjn.centurhuila.utilidades;

/**
 * Created by CAMILO on 4/4/2018.
 */

public class Utilidades {

    //constantes campos tabla usuario
    public static final String TABLA_USUARIO=" usuario ";
    public static final String CAMPO_ID=" id ";
    public static final String CAMPO_NOMBRE=" nombre ";

    public static final String CREAR_TABLA_USUARIO="CREATE TABLE" +
            " "+TABLA_USUARIO+"("+CAMPO_ID+" "+
            "INTEGER, "+CAMPO_NOMBRE+" TEXT)";
}
