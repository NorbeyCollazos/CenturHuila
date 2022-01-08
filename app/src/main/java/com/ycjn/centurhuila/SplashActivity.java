package com.ycjn.centurhuila;

import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    private final int DURACION_SPLASH =5000;
    ImageView imagen;
    //Handle para retardar acciones
    private Handler mHandler= new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_activity);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);//queda pantalla completa

/*
        //cambio de fuente
        String fuente = "fuentes/tit.TTF";
        this.action = Typeface.createFromAsset(getAssets(), fuente);
        String fuente2 = "fuentes/txt.TTF";
        this.action2 = Typeface.createFromAsset(getAssets(), fuente2);
*/
        //titulo = (TextView) findViewById(R.id.titulo1);
        //titulo.setTypeface(action);
        //parraf = (TextView) findViewById(R.id.eslogan);
        //parraf.setTypeface(action2);

        //animacion
        imagen = (ImageView) findViewById(R.id.ImageTrans);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, ManualUsuario.class);
                startActivity(intent);
                finish();
            }

            ;
        }, DURACION_SPLASH);

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                imagen.setVisibility(View.VISIBLE);
                trans();
            }
        },1200);
    }





    public void trans(){
        Animation animation = AnimationUtils.loadAnimation(this,R.anim.trans);
        imagen.startAnimation(animation);
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                imagen.setVisibility(View.GONE);

            }
        },2400);
    }}
