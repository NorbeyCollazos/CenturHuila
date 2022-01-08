package com.ycjn.centurhuila;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.ycjn.centurhuila.utilidades.Utilidades;


/**
 * Created by CAMILO on 4/4/2018.
 */

public class ConexionSQLiteHelper extends SQLiteOpenHelper {
    public ConexionSQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Utilidades.CREAR_TABLA_USUARIO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS usuario");
        onCreate(db);

    }
}
