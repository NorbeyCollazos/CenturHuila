package com.ycjn.centurhuila;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import java.util.Locale;

public class Idioma extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_idioma);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.azul_medio)); //Define color blanco.
        }

        ImageButton atras = (ImageButton) findViewById(R.id.flecha);
        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        AppCompatButton btncalificar = (AppCompatButton)findViewById(R.id.btncalificar);
        btncalificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String appPackageName =  "com.ycjn.centurhuila"; // getPackageName() from Context or Activity object
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                } catch (android.content.ActivityNotFoundException anfe) {}
            }
        });

    }

    public void Espanol(View view) {

        Locale idiom_es = new Locale("es", "ES");
        Locale.setDefault(idiom_es);
        Configuration config_es = new Configuration();
        config_es.locale = idiom_es;
        getBaseContext().getResources().updateConfiguration(config_es, getBaseContext().getResources().getDisplayMetrics());
        Intent ini = new Intent(Idioma.this,InicioActivity.class);
        startActivity(ini);


    }

    public void Ingles(View view) {

        Locale idiom_en = new Locale("en", "EN");
        Locale.setDefault(idiom_en);
        Configuration config_en = new Configuration();
        config_en.locale = idiom_en;
        getBaseContext().getResources().updateConfiguration(config_en, getBaseContext().getResources().getDisplayMetrics());
        Intent ini = new Intent(Idioma.this,InicioActivity.class);
        startActivity(ini);


    }

    public void Portugues(View view) {

        Locale idiom_en = new Locale("pt", "PT");
        Locale.setDefault(idiom_en);
        Configuration config_en = new Configuration();
        config_en.locale = idiom_en;
        getBaseContext().getResources().updateConfiguration(config_en, getBaseContext().getResources().getDisplayMetrics());
        //Intent ini = new Intent(Idioma.this,InicioActivity.class);
        //startActivity(ini);
        finish();
    }
}
